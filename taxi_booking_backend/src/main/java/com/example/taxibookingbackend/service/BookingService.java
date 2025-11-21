package com.example.taxibookingbackend.service;

import com.example.taxibookingbackend.domain.*;
import com.example.taxibookingbackend.dto.BookingDtos;
import com.example.taxibookingbackend.exception.BadRequestException;
import com.example.taxibookingbackend.exception.NotFoundException;
import com.example.taxibookingbackend.repository.BookingRepository;
import com.example.taxibookingbackend.repository.DriverRepository;
import com.example.taxibookingbackend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.List;

/**
 * Booking lifecycle and assignment service.
 */
@Service
public class BookingService {
    private static final Logger log = LoggerFactory.getLogger(BookingService.class);

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final DriverRepository driverRepository;

    public BookingService(BookingRepository bookingRepository,
                          UserRepository userRepository,
                          DriverRepository driverRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.driverRepository = driverRepository;
    }

    // PUBLIC_INTERFACE
    public Booking createBooking(BookingDtos.CreateBookingRequest req) {
        /** Create a booking in REQUESTED state. */
        User user = userRepository.findById(req.userId)
                .orElseThrow(() -> new NotFoundException("User not found: " + req.userId));

        Booking b = new Booking();
        b.setUser(user);
        b.setPickupLocation(req.pickupLocation);
        b.setDropoffLocation(req.dropoffLocation);
        b.setStatus(BookingStatus.REQUESTED);
        Booking saved = bookingRepository.save(b);
        log.info("Created booking id={} for user={}", saved.getId(), user.getId());
        return saved;
    }

    // PUBLIC_INTERFACE
    public Booking assignDriver(Long bookingId, Long driverId) {
        /** Assign an AVAILABLE driver to a REQUESTED booking. */
        Booking b = getById(bookingId);
        if (b.getStatus() != BookingStatus.REQUESTED) {
            throw new BadRequestException("Only REQUESTED bookings can be assigned.");
        }
        Driver d = driverRepository.findById(driverId)
                .orElseThrow(() -> new NotFoundException("Driver not found: " + driverId));
        if (d.getStatus() != DriverStatus.AVAILABLE) {
            throw new BadRequestException("Driver is not available.");
        }

        // Ensure driver is not already assigned/in progress
        long active = bookingRepository.countByDriver_IdAndStatusIn(
                driverId,
                List.copyOf(EnumSet.of(BookingStatus.ASSIGNED, BookingStatus.IN_PROGRESS))
        );
        if (active > 0) {
            throw new BadRequestException("Driver already has an active booking.");
        }

        b.setDriver(d);
        b.setStatus(BookingStatus.ASSIGNED);
        d.setStatus(DriverStatus.UNAVAILABLE);
        driverRepository.save(d);
        Booking saved = bookingRepository.save(b);
        log.info("Assigned driver={} to booking={}", driverId, bookingId);
        return saved;
    }

    // PUBLIC_INTERFACE
    public Booking startRide(Long bookingId) {
        /** Transition ASSIGNED booking to IN_PROGRESS. */
        Booking b = getById(bookingId);
        if (b.getStatus() != BookingStatus.ASSIGNED) {
            throw new BadRequestException("Only ASSIGNED bookings can be started.");
        }
        b.setStatus(BookingStatus.IN_PROGRESS);
        Booking saved = bookingRepository.save(b);
        log.info("Booking {} started", bookingId);
        return saved;
    }

    // PUBLIC_INTERFACE
    public Booking completeRide(Long bookingId) {
        /** Transition IN_PROGRESS booking to COMPLETED and free driver. */
        Booking b = getById(bookingId);
        if (b.getStatus() != BookingStatus.IN_PROGRESS) {
            throw new BadRequestException("Only IN_PROGRESS bookings can be completed.");
        }
        b.setStatus(BookingStatus.COMPLETED);
        Booking saved = bookingRepository.save(b);

        if (b.getDriver() != null) {
            Driver d = b.getDriver();
            d.setStatus(DriverStatus.AVAILABLE);
            driverRepository.save(d);
        }
        log.info("Booking {} completed", bookingId);
        return saved;
    }

    // PUBLIC_INTERFACE
    public Booking cancelBooking(Long bookingId) {
        /** Cancel booking when REQUESTED or ASSIGNED, release driver if any. */
        Booking b = getById(bookingId);
        if (!(b.getStatus() == BookingStatus.REQUESTED || b.getStatus() == BookingStatus.ASSIGNED)) {
            throw new BadRequestException("Only REQUESTED or ASSIGNED bookings can be canceled.");
        }
        b.setStatus(BookingStatus.CANCELED);
        Booking saved = bookingRepository.save(b);

        if (b.getDriver() != null) {
            Driver d = b.getDriver();
            d.setStatus(DriverStatus.AVAILABLE);
            driverRepository.save(d);
        }
        log.info("Booking {} canceled", bookingId);
        return saved;
    }

    // PUBLIC_INTERFACE
    public Booking getById(Long id) {
        /** Get booking by id or throw NotFoundException. */
        return bookingRepository.findById(id).orElseThrow(() -> new NotFoundException("Booking not found: " + id));
    }

    // PUBLIC_INTERFACE
    public List<Booking> listByUser(Long userId) {
        /** List bookings by user id. */
        return bookingRepository.findByUser_Id(userId);
    }

    // PUBLIC_INTERFACE
    public List<Booking> listByDriver(Long driverId) {
        /** List bookings by driver id. */
        return bookingRepository.findByDriver_Id(driverId);
    }
}
