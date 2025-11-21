package com.example.taxibookingbackend.repository;

import com.example.taxibookingbackend.domain.Booking;
import com.example.taxibookingbackend.domain.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Booking repository.
 */
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser_Id(Long userId);
    List<Booking> findByDriver_Id(Long driverId);
    long countByDriver_IdAndStatusIn(Long driverId, List<BookingStatus> statuses);
}
