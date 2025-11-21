package com.example.taxibookingbackend.service;

import com.example.taxibookingbackend.domain.Driver;
import com.example.taxibookingbackend.domain.DriverStatus;
import com.example.taxibookingbackend.domain.Vehicle;
import com.example.taxibookingbackend.dto.DriverDtos;
import com.example.taxibookingbackend.exception.NotFoundException;
import com.example.taxibookingbackend.repository.DriverRepository;
import com.example.taxibookingbackend.repository.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Driver operations service.
 */
@Service
public class DriverService {
    private static final Logger log = LoggerFactory.getLogger(DriverService.class);

    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;

    public DriverService(DriverRepository driverRepository, VehicleRepository vehicleRepository) {
        this.driverRepository = driverRepository;
        this.vehicleRepository = vehicleRepository;
    }

    // PUBLIC_INTERFACE
    public Driver createDriver(DriverDtos.CreateDriverRequest req) {
        /** Create a new driver with vehicle. Persist vehicle first to have a managed entity, then link in Driver. */
        Vehicle v = new Vehicle();
        v.setMake(req.vehicleMake);
        v.setModel(req.vehicleModel);
        v.setPlateNumber(req.plateNumber);
        Vehicle managedVehicle = vehicleRepository.save(v);

        Driver d = new Driver();
        d.setName(req.name);
        d.setStatus(DriverStatus.AVAILABLE);
        d.setVehicle(managedVehicle);
        Driver saved = driverRepository.save(d);
        log.info("Created driver id={} with vehicle id={}", saved.getId(), managedVehicle.getId());
        return saved;
    }

    // PUBLIC_INTERFACE
    public Driver getById(Long id) {
        /** Get driver by id or throw NotFoundException. */
        return driverRepository.findById(id).orElseThrow(() -> new NotFoundException("Driver not found: " + id));
    }

    // PUBLIC_INTERFACE
    public List<Driver> listAll() {
        /** List all drivers. */
        return driverRepository.findAll();
    }

    // PUBLIC_INTERFACE
    public List<Driver> listAvailable() {
        /** List available drivers. */
        return driverRepository.findByStatus(DriverStatus.AVAILABLE);
    }

    // PUBLIC_INTERFACE
    public Driver updateAvailability(Long id, DriverStatus status) {
        /** Update driver availability status. */
        Driver d = getById(id);
        d.setStatus(status);
        return driverRepository.save(d);
    }
}
