package com.example.taxibookingbackend.service;

import com.example.taxibookingbackend.domain.User;
import com.example.taxibookingbackend.dto.UserDtos;
import com.example.taxibookingbackend.exception.NotFoundException;
import com.example.taxibookingbackend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service handling user operations.
 */
@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // PUBLIC_INTERFACE
    public User createUser(UserDtos.CreateUserRequest req) {
        /** Create a new user from request. */
        User u = new User();
        u.setName(req.name);
        u.setEmail(req.email);
        User saved = userRepository.save(u);
        log.info("Created user id={}", saved.getId());
        return saved;
    }

    // PUBLIC_INTERFACE
    public User getById(Long id) {
        /** Get user by id or throw NotFoundException. */
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found: " + id));
    }

    // PUBLIC_INTERFACE
    public List<User> listAll() {
        /** List all users. */
        return userRepository.findAll();
    }
}
