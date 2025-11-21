package com.example.taxibookingbackend.exception;

/**
 * Thrown on invalid input or state transitions.
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) { super(message); }
}
