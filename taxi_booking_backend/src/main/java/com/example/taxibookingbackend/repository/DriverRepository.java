package com.example.taxibookingbackend.repository;

import com.example.taxibookingbackend.domain.Driver;
import com.example.taxibookingbackend.domain.DriverStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Driver repository.
 */
public interface DriverRepository extends JpaRepository<Driver, Long> {
    List<Driver> findByStatus(DriverStatus status);
}
