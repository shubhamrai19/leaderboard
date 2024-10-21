package com.shubham.leaderboard.Controller;

import com.shubham.leaderboard.Model.User;
import com.shubham.leaderboard.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            User createdUser = userService.create(user);
            return ResponseEntity.ok(createdUser);
        } catch (Exception e) {
            log.error("Error creating user: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user) {
        try {
            user.setId(id);
            User updatedUser = userService.update(user);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            log.error("Error updating user with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") int id) {
        try {
            userService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Error deleting user with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        try {
            Optional<User> user = userService.getId(id);
            return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            log.error("Error fetching user with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userService.getAllPlayer();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            log.error("Error fetching all users: {}", e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }
}
