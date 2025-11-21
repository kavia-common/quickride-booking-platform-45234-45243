package com.example.taxibookingbackend.controller;

import com.example.taxibookingbackend.domain.Driver;
import com.example.taxibookingbackend.domain.DriverStatus;
import com.example.taxibookingbackend.dto.DriverDtos;
import com.example.taxibookingbackend.mapper.Mappers;
import com.example.taxibookingbackend.service.DriverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/**
 * REST endpoints for driver management and availability.
 */
@RestController
@RequestMapping("/api/drivers")
@Tag(name = "Drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) { this.driverService = driverService; }

    // PUBLIC_INTERFACE
    @PostMapping
    @Operation(summary = "Create driver", description = "Registers a new driver with vehicle.")
    public ResponseEntity<DriverDtos.DriverResponse> create(@Valid @RequestBody DriverDtos.CreateDriverRequest req) {
        /** Create driver and return 201. */
        Driver d = driverService.createDriver(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(Mappers.toDriverResponse(d));
    }

    // PUBLIC_INTERFACE
    @GetMapping("/{id}")
    @Operation(summary = "Get driver by id", description = "Retrieve driver by ID.")
    public DriverDtos.DriverResponse get(@PathVariable Long id) {
        /** Get driver by id. */
        return Mappers.toDriverResponse(driverService.getById(id));
    }

    // PUBLIC_INTERFACE
    @GetMapping
    @Operation(summary = "List drivers", description = "List all drivers.")
    public java.util.List<DriverDtos.DriverResponse> list() {
        /** List all drivers. */
        return driverService.listAll().stream().map(Mappers::toDriverResponse).collect(Collectors.toList());
    }

    // PUBLIC_INTERFACE
    @GetMapping("/available")
    @Operation(summary = "List available drivers", description = "List drivers with AVAILABLE status.")
    public java.util.List<DriverDtos.DriverResponse> listAvailable() {
        /** List available drivers. */
        return driverService.listAvailable().stream().map(Mappers::toDriverResponse).collect(Collectors.toList());
    }

    // PUBLIC_INTERFACE
    @PatchMapping("/{id}/availability")
    @Operation(summary = "Update driver availability", description = "Update driver availability status.")
    public DriverDtos.DriverResponse updateAvailability(@PathVariable Long id,
                                                        @Valid @RequestBody DriverDtos.UpdateAvailabilityRequest req) {
        /** Update availability. */
        Driver d = driverService.updateAvailability(id, req.status);
        return Mappers.toDriverResponse(d);
    }
}
