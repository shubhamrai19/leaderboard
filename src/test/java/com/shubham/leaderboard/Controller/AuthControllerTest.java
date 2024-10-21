package com.shubham.leaderboard.Controller;

import com.shubham.leaderboard.Model.User;
import com.shubham.leaderboard.Service.AuthService;
import com.shubham.leaderboard.dto.SignInRequest;
import com.shubham.leaderboard.dto.SignInResponse;
import com.shubham.leaderboard.dto.SignUpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    private SignInRequest signInRequest;
    private SignInResponse signInResponse;
    private SignUpRequest signUpRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        signInRequest = new SignInRequest("abc@gmail.com", "password123");
        signInResponse = new SignInResponse(/* parameters */); // Add appropriate parameters
        signUpRequest = new SignUpRequest("abc@gmail.com", "John Doe", "password123");
    }

    @Test
    void login_success() {
        when(authService.signIn(signInRequest)).thenReturn(signInResponse);

        ResponseEntity<?> response = authController.login(signInRequest);

        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(signInResponse, response.getBody());
    }

    @Test
    void login_failure() {
        when(authService.signIn(signInRequest)).thenThrow(new RuntimeException("Invalid credentials"));

        ResponseEntity<?> response = authController.login(signInRequest);

        assertEquals(500, response.getStatusCodeValue());
        assertEquals("Invalid credentials", response.getBody());
    }

    @Test
    void signUp_success() {
        when(authService.signup(signUpRequest)).thenReturn(new User());

        ResponseEntity<?> response = authController.signUp(signUpRequest);

        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void signUp_failure() {
        when(authService.signup(any(SignUpRequest.class))).thenThrow(new RuntimeException("SignUp failed"));

        ResponseEntity<?> response = authController.signUp(signUpRequest);

        assertEquals(500, response.getStatusCodeValue());
        assertEquals("SignUp failed", response.getBody());
    }
}
