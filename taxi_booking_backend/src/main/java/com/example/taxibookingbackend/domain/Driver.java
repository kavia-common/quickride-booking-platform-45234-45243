package com.example.taxibookingbackend.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

/**
 * Driver entity.
 */
@Entity
@Table(name = "drivers")
public class Driver {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DriverStatus status = DriverStatus.AVAILABLE;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Vehicle vehicle;

    // Constructors
    public Driver() {}

    public Driver(Long id, String name, DriverStatus status, Vehicle vehicle) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.vehicle = vehicle;
    }

    // Getters/setters
    public Long getId() {  return id; }
    public void setId(Long id) {  this.id = id; }

    public String getName() {  return name; }
    public void setName(String name) {  this.name = name; }

    public DriverStatus getStatus() {  return status; }
    public void setStatus(DriverStatus status) {  this.status = status; }

    public Vehicle getVehicle() {  return vehicle; }
    public void setVehicle(Vehicle vehicle) {  this.vehicle = vehicle; }
}
