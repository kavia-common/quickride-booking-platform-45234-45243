package com.example.taxibookingbackend.controller;

import com.example.taxibookingbackend.domain.Booking;
import com.example.taxibookingbackend.dto.BookingDtos;
import com.example.taxibookingbackend.mapper.Mappers;
import com.example.taxibookingbackend.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/**
 * REST endpoints for bookings and lifecycle transitions.
 */
@RestController
@RequestMapping("/api/bookings")
@Tag(name = "Bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) { this.bookingService = bookingService; }

    // PUBLIC_INTERFACE
    @PostMapping
    @Operation(summary = "Create booking", description = "Create a booking in REQUESTED state.")
    public ResponseEntity<BookingDtos.BookingResponse> create(@Valid @RequestBody BookingDtos.CreateBookingRequest req) {
        /** Create booking => 201. */
        Booking b = bookingService.createBooking(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(Mappers.toBookingResponse(b));
    }

    // PUBLIC_INTERFACE
    @PostMapping("/{id}/assign")
    @Operation(summary = "Assign driver", description = "Assign an available driver to a REQUESTED booking and transition to ASSIGNED.")
    public BookingDtos.BookingResponse assignDriver(@PathVariable Long id,
                                                    @Valid @RequestBody BookingDtos.AssignDriverRequest req) {
        /** Assign driver to booking. */
        return Mappers.toBookingResponse(bookingService.assignDriver(id, req.driverId));
    }

    // PUBLIC_INTERFACE
    @PostMapping("/{id}/start")
    @Operation(summary = "Start ride", description = "Start an ASSIGNED booking -> IN_PROGRESS.")
    public BookingDtos.BookingResponse start(@PathVariable Long id) {
        /** Start ride. */
        return Mappers.toBookingResponse(bookingService.startRide(id));
    }

    // PUBLIC_INTERFACE
    @PostMapping("/{id}/complete")
    @Operation(summary = "Complete ride", description = "Complete an IN_PROGRESS booking -> COMPLETED and mark driver available.")
    public BookingDtos.BookingResponse complete(@PathVariable Long id) {
        /** Complete ride. */
        return Mappers.toBookingResponse(bookingService.completeRide(id));
    }

    // PUBLIC_INTERFACE
    @PostMapping("/{id}/cancel")
    @Operation(summary = "Cancel booking", description = "Cancel a booking when REQUESTED or ASSIGNED.")
    public BookingDtos.BookingResponse cancel(@PathVariable Long id) {
        /** Cancel booking. */
        return Mappers.toBookingResponse(bookingService.cancelBooking(id));
    }

    // PUBLIC_INTERFACE
    @GetMapping("/{id}")
    @Operation(summary = "Get booking", description = "Get booking by ID.")
    public BookingDtos.BookingResponse get(@PathVariable Long id) {
        /** Get booking. */
        return Mappers.toBookingResponse(bookingService.getById(id));
    }

    // PUBLIC_INTERFACE
    @GetMapping("/by-user/{userId}")
    @Operation(summary = "List bookings by user", description = "List bookings for a given user id.")
    public java.util.List<BookingDtos.BookingResponse> listByUser(@PathVariable Long userId) {
        /** List bookings by user. */
        return bookingService.listByUser(userId).stream().map(Mappers::toBookingResponse).collect(Collectors.toList());
    }

    // PUBLIC_INTERFACE
    @GetMapping("/by-driver/{driverId}")
    @Operation(summary = "List bookings by driver", description = "List bookings for a given driver id.")
    public java.util.List<BookingDtos.BookingResponse> listByDriver(@PathVariable Long driverId) {
        /** List bookings by driver. */
        return bookingService.listByDriver(driverId).stream().map(Mappers::toBookingResponse).collect(Collectors.toList());
    }
}
