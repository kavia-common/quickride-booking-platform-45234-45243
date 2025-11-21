package com.example.taxibookingbackend.dto;

import com.example.taxibookingbackend.domain.BookingStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTOs for Booking API.
 */
public class BookingDtos {

    public static class CreateBookingRequest {
        @Schema(description = "User ID making the request", example = "1")
        @NotNull
        public Long userId;

        @Schema(description = "Pickup location", example = "123 Main St")
        @NotBlank
        public String pickupLocation;

        @Schema(description = "Dropoff location", example = "456 Oak Ave")
        @NotBlank
        public String dropoffLocation;
    }

    public static class AssignDriverRequest {
        @Schema(description = "Driver ID to assign", example = "2")
        @NotNull
        public Long driverId;
    }

    public static class BookingResponse {
        public Long id;
        public Long userId;
        public Long driverId;
        public String pickupLocation;
        public String dropoffLocation;
        public BookingStatus status;
    }
}
