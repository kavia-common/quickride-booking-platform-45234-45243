package com.example.taxibookingbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * DTOs for User API.
 */
public class UserDtos {

    public static class CreateUserRequest {
        @Schema(description = "User name", example = "Alice")
        @NotBlank
        public String name;

        @Schema(description = "Email address", example = "alice@example.com")
        @Email @NotBlank
        public String email;
    }

    public static class UserResponse {
        public Long id;
        public String name;
        public String email;
    }
}
