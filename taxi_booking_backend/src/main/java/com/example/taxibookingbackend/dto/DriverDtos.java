package com.example.taxibookingbackend.dto;

import com.example.taxibookingbackend.domain.DriverStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTOs for Driver API.
 */
public class DriverDtos {

    public static class CreateDriverRequest {
        @Schema(description = "Driver name", example = "Bob Driver")
        @NotBlank
        public String name;

        @Schema(description = "Vehicle make", example = "Toyota")
        @NotBlank
        public String vehicleMake;

        @Schema(description = "Vehicle model", example = "Prius")
        @NotBlank
        public String vehicleModel;

        @Schema(description = "License plate number", example = "ABC-1234")
        @NotBlank
        public String plateNumber;
    }

    public static class UpdateAvailabilityRequest {
        @Schema(description = "Driver availability", example = "AVAILABLE")
        @NotNull
        public DriverStatus status;
    }

    public static class DriverResponse {
        public Long id;
        public String name;
        public DriverStatus status;
        public VehicleDto vehicle;
    }

    public static class VehicleDto {
        public Long id;
        public String make;
        public String model;
        public String plateNumber;
    }
}
