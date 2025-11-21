package com.example.taxibookingbackend.exception;

/**
 * Thrown when a requested entity is not found.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) { super(message); }
}
