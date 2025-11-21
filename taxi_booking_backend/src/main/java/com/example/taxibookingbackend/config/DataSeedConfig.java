package com.example.taxibookingbackend.config;

import com.example.taxibookingbackend.domain.Driver;
import com.example.taxibookingbackend.domain.DriverStatus;
import com.example.taxibookingbackend.domain.User;
import com.example.taxibookingbackend.domain.Vehicle;
import com.example.taxibookingbackend.repository.DriverRepository;
import com.example.taxibookingbackend.repository.UserRepository;
import com.example.taxibookingbackend.repository.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Seeds sample users and drivers on startup for preview.
 */
@Configuration
public class DataSeedConfig {
    private static final Logger log = LoggerFactory.getLogger(DataSeedConfig.class);

    @Bean
    CommandLineRunner seed(UserRepository users, DriverRepository drivers, VehicleRepository vehicles) {
        return args -> {
            if (users.count() == 0) {
                users.save(new User(null, "Alice", "alice@example.com"));
                users.save(new User(null, "Bob", "bob@example.com"));
            }

            if (drivers.count() == 0) {
                Vehicle v1 = vehicles.save(new Vehicle(null, "Toyota", "Prius", "ABC-123"));
                Vehicle v2 = vehicles.save(new Vehicle(null, "Honda", "Civic", "XYZ-789"));
                drivers.save(new Driver(null, "Charlie", DriverStatus.AVAILABLE, v1));
                drivers.save(new Driver(null, "Diana", DriverStatus.AVAILABLE, v2));
            }

            log.info("Seed data ready: users={}, drivers={}", users.count(), drivers.count());
        };
    }
}
