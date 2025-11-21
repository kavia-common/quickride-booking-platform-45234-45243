package com.example.taxibookingbackend.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * User entity representing a customer using the taxi booking platform.
 */
@Entity
@Table(name = "users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Email
    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    // Constructors
    public User() {}

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() {  return name; }
    public void setName(String name) {  this.name = name; }

    public String getEmail() {  return email; }
    public void setEmail(String email) {  this.email = email; }
}
