package com.example.taxibookingbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot entrypoint for Taxi Booking Backend.
 *
 * Starts the application on the configured port (see application.properties) and
 * scans the com.example.taxibookingbackend package for components.
 */
@SpringBootApplication
public class TaxibookingbackendApplication {

    // PUBLIC_INTERFACE
    public static void main(String[] args) {
        /** Bootstraps the Spring application context. */
        SpringApplication.run(TaxibookingbackendApplication.class, args);
    }
}
