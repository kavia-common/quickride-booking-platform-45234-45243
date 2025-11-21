package com.example.taxibookingbackend.repository;

import com.example.taxibookingbackend.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Vehicle repository.
 */
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
