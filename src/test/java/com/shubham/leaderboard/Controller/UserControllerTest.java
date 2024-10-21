package com.shubham.leaderboard.Controller;

import com.shubham.leaderboard.Model.Score;
import com.shubham.leaderboard.Model.User;
import com.shubham.leaderboard.Service.ScoreService;
import com.shubham.leaderboard.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1);
        user.setFullName("TEST");
    }

    @Test
    void createScore_success() {
        when(userService.create(user)).thenReturn(user);

        ResponseEntity<?> response = userController.createUser(user);

        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(user, response.getBody());
    }

    @Test
    void createScore_failure() {
        when(userService.create(any(User.class))).thenThrow(new RuntimeException("Failed to create user"));

        ResponseEntity<?> response = userController.createUser(user);

        assertEquals(500, response.getStatusCodeValue());
        assertEquals("Failed to create user", response.getBody());
    }


    @Test
    void getUserById_success() {
        when(userService.getId(1)).thenReturn(java.util.Optional.of(user));

        ResponseEntity<?> response = userController.getUserById(1);

        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(user, response.getBody());
    }

    @Test
    void getUserById_notFound() {
        when(userService.getId(1)).thenReturn(java.util.Optional.empty());

        ResponseEntity<?> response = userController.getUserById(1);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }
}

