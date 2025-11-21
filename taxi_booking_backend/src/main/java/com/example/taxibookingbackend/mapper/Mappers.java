package com.example.taxibookingbackend.mapper;

import com.example.taxibookingbackend.domain.Booking;
import com.example.taxibookingbackend.domain.Driver;
import com.example.taxibookingbackend.domain.User;
import com.example.taxibookingbackend.domain.Vehicle;
import com.example.taxibookingbackend.dto.BookingDtos;
import com.example.taxibookingbackend.dto.DriverDtos;
import com.example.taxibookingbackend.dto.UserDtos;

/**
 * Simple manual mappers between entities and DTOs.
 */
public class Mappers {

    // PUBLIC_INTERFACE
    public static UserDtos.UserResponse toUserResponse(User u) {
        /** Map User entity to response DTO. */
        UserDtos.UserResponse r = new UserDtos.UserResponse();
        r.id = u.getId();
        r.name = u.getName();
        r.email = u.getEmail();
        return r;
    }

    // PUBLIC_INTERFACE
    public static DriverDtos.DriverResponse toDriverResponse(Driver d) {
        /** Map Driver entity to response DTO including vehicle. */
        DriverDtos.DriverResponse r = new DriverDtos.DriverResponse();
        r.id = d.getId();
        r.name = d.getName();
        r.status = d.getStatus();
        if (d.getVehicle() != null) {
            DriverDtos.VehicleDto v = new DriverDtos.VehicleDto();
            v.id = d.getVehicle().getId();
            v.make = d.getVehicle().getMake();
            v.model = d.getVehicle().getModel();
            v.plateNumber = d.getVehicle().getPlateNumber();
            r.vehicle = v;
        }
        return r;
    }

    // PUBLIC_INTERFACE
    public static BookingDtos.BookingResponse toBookingResponse(Booking b) {
        /** Map Booking entity to response DTO. */
        BookingDtos.BookingResponse r = new BookingDtos.BookingResponse();
        r.id = b.getId();
        r.userId = b.getUser() != null ? b.getUser().getId() : null;
        r.driverId = b.getDriver() != null ? b.getDriver().getId() : null;
        r.pickupLocation = b.getPickupLocation();
        r.dropoffLocation = b.getDropoffLocation();
        r.status = b.getStatus();
        return r;
    }

    // PUBLIC_INTERFACE
    public static Vehicle fromCreateDriver(DriverDtos.CreateDriverRequest req) {
        /** Create a Vehicle entity from CreateDriverRequest fields. */
        Vehicle v = new Vehicle();
        v.setMake(req.vehicleMake);
        v.setModel(req.vehicleModel);
        v.setPlateNumber(req.plateNumber);
        return v;
    }
}
