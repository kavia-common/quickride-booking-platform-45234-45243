package com.example.taxibookingbackend.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

/**
 * Vehicle entity for driver vehicles.
 */
@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String make;

    @NotBlank
    @Column(nullable = false)
    private String model;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String plateNumber;

    // Constructors
    public Vehicle() {}

    public Vehicle(Long id, String make, String model, String plateNumber) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.plateNumber = plateNumber;
    }

    // Getters/setters
    public Long getId() {  return id; }
    public void setId(Long id) {  this.id = id; }

    public String getMake() {  return make; }
    public void setMake(String make) {  this.make = make; }

    public String getModel() {  return model; }
    public void setModel(String model) {  this.model = model; }

    public String getPlateNumber() {  return plateNumber; }
    public void setPlateNumber(String plateNumber) {  this.plateNumber = plateNumber; }
}
