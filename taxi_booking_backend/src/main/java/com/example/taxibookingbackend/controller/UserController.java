package com.example.taxibookingbackend.controller;

import com.example.taxibookingbackend.domain.User;
import com.example.taxibookingbackend.dto.UserDtos;
import com.example.taxibookingbackend.mapper.Mappers;
import com.example.taxibookingbackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/**
 * REST endpoints for user registration and retrieval.
 */
@RestController
@RequestMapping("/api/users")
@Tag(name = "Users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    // PUBLIC_INTERFACE
    @PostMapping
    @Operation(summary = "Create user", description = "Registers a new user.")
    public ResponseEntity<UserDtos.UserResponse> create(@Valid @RequestBody UserDtos.CreateUserRequest req) {
        /** Create a new user and return 201. */
        User saved = userService.createUser(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(Mappers.toUserResponse(saved));
    }

    // PUBLIC_INTERFACE
    @GetMapping("/{id}")
    @Operation(summary = "Get user by id", description = "Retrieve user by ID.")
    public UserDtos.UserResponse get(@PathVariable Long id) {
        /** Get user by id. */
        return Mappers.toUserResponse(userService.getById(id));
    }

    // PUBLIC_INTERFACE
    @GetMapping
    @Operation(summary = "List users", description = "List all registered users.")
    public java.util.List<UserDtos.UserResponse> list() {
        /** List all users. */
        return userService.listAll().stream().map(Mappers::toUserResponse).collect(Collectors.toList());
    }
}
