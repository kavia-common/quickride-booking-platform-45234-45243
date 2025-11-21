package com.example.taxibookingbackend.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.Instant;

/**
 * Booking entity representing a ride request.
 */
@Entity
@Table(name = "bookings")
public class Booking {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User who requested the ride
    @ManyToOne(optional = false)
    private User user;

    // Driver assigned (nullable until assigned)
    @ManyToOne
    private Driver driver;

    @NotBlank
    @Column(nullable = false)
    private String pickupLocation;

    @NotBlank
    @Column(nullable = false)
    private String dropoffLocation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status = BookingStatus.REQUESTED;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    private Instant updatedAt = Instant.now();

    // Constructors
    public Booking() {}

    public Booking(Long id, User user, Driver driver, String pickupLocation, String dropoffLocation, BookingStatus status) {
        this.id = id;
        this.user = user;
        this.driver = driver;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.status = status;
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = Instant.now();
    }

    // Getters/setters
    public Long getId() {  return id; }
    public void setId(Long id) {  this.id = id; }

    public User getUser() {  return user; }
    public void setUser(User user) {  this.user = user; }

    public Driver getDriver() {  return driver; }
    public void setDriver(Driver driver) {  this.driver = driver; }

    public String getPickupLocation() {  return pickupLocation; }
    public void setPickupLocation(String pickupLocation) {  this.pickupLocation = pickupLocation; }

    public String getDropoffLocation() {  return dropoffLocation; }
    public void setDropoffLocation(String dropoffLocation) {  this.dropoffLocation = dropoffLocation; }

    public BookingStatus getStatus() {  return status; }
    public void setStatus(BookingStatus status) {  this.status = status; }

    public Instant getCreatedAt() {  return createdAt; }
    public void setCreatedAt(Instant createdAt) {  this.createdAt = createdAt; }

    public Instant getUpdatedAt() {  return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) {  this.updatedAt = updatedAt; }
}
