package com.shubham.leaderboard.Service.impl;

import com.shubham.leaderboard.Model.User;
import com.shubham.leaderboard.Repository.UserRepository;
import com.shubham.leaderboard.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(User user) {
        try {
            log.info("Creating new user: {}", user);
            User nUser = new User();
            nUser.setEmailId(user.getEmailId());
            nUser.setPasswords(passwordEncoder.encode(user.getPassword()));
            nUser.setFullName(user.getFullName());
             userRepository.save(nUser);
             return nUser;
        } catch (Exception e) {
            log.error("Error creating user: {}", e.getMessage());
            throw new RuntimeException("Failed to create user");
        }
    }

    @Override
    public User update(User user) {
        try {
            log.info("Updating user with ID: {}", user.getId());
            Optional<User> existingUser = userRepository.findById(user.getId());

            if (existingUser.isPresent()) {
                userRepository.update(user); // Update the user
                return user;
            } else {
                log.warn("User with ID: {} not found", user.getId());
                throw new RuntimeException("User not found for update");
            }
        } catch (Exception e) {
            log.error("Error updating user with ID: {}: {}", user.getId(), e.getMessage());
            throw new RuntimeException("Failed to update user");
        }
    }

    @Override
    public void delete(int id) {
        try {
            log.info("Deleting user with ID: {}", id);
            Optional<User> existingUser = userRepository.findById(id);

            if (existingUser.isPresent()) {
                userRepository.delete(id); // Delete the user
                log.info("User with ID: {} deleted successfully", id);
            } else {
                log.warn("User with ID: {} not found", id);
                throw new RuntimeException("User not found for deletion");
            }
        } catch (Exception e) {
            log.error("Error deleting user with ID: {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to delete user");
        }
    }

    @Override
    public Optional<User> getId(int id) {
        try {
            log.info("Fetching user with ID: {}", id);
            return userRepository.findById(id);
        } catch (Exception e) {
            log.error("Error fetching user with ID: {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to fetch user");
        }
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllPlayer() {
        try {
            log.info("Fetching all users");
            return userRepository.findAll();
        } catch (Exception e) {
            log.error("Error fetching all users: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch all users");
        }
    }
}
