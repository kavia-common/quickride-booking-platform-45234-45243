package com.example.taxibookingbackend.domain;

/**
 * Booking lifecycle status.
 */
public enum BookingStatus {
    REQUESTED,
    ASSIGNED,
    IN_PROGRESS,
    COMPLETED,
    CANCELED
}
