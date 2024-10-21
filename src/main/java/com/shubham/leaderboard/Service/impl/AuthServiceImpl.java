package com.shubham.leaderboard.Service.impl;

import com.shubham.leaderboard.Model.User;
import com.shubham.leaderboard.Service.AuthService;
import com.shubham.leaderboard.Service.UserService;
import com.shubham.leaderboard.Service.impl.jwt.JwtServiceImpl;
import com.shubham.leaderboard.config.ApplicationBeans;
import com.shubham.leaderboard.dto.SignInRequest;
import com.shubham.leaderboard.dto.SignInResponse;
import com.shubham.leaderboard.dto.SignUpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final ApplicationBeans applicationBeans;
    private final AuthenticationManager authenticationManager;
    private final JwtServiceImpl jwtService;


    @Autowired
    public AuthServiceImpl(UserService userService, ApplicationBeans applicationBeans, AuthenticationManager authenticationManager, JwtServiceImpl jwtService) {
        this.userService = userService;
        this.applicationBeans = applicationBeans;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public User signup(SignUpRequest request) {
        try {
            User user = new User();
            user.setFullName(request.getFullName());
            user.setEmailId(request.getEmail());
            user.setPasswords(request.getPassword());
            return userService.create(user);
        } catch (Exception e) {
            log.error("Error creating user: {}", e.getMessage());
            throw new RuntimeException("Failed to create user");
        }
    }

    @Override
    public SignInResponse signIn(SignInRequest inRequest) {
        try {
            Optional<User> user = userService.getByEmail(inRequest.getEmail());
            if (user.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not present,SignUp before SignIn");
            }
            User userPresent = user.get();
            log.info("User found: ID = {}, Email = {}, FullName = {}", userPresent.getId(), userPresent.getEmailId(), userPresent.getFullName());
            Authentication authentication =authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(inRequest.getEmail(), inRequest.getPassword())
            );

            User authenticatedUser = (User) authentication.getPrincipal();
            String token = jwtService.generateToken(authenticatedUser);

            SignInResponse signInResponse=new SignInResponse();
            signInResponse.setId(userPresent.getId());
            signInResponse.setEmail(userPresent.getEmailId());
            signInResponse.setToken(token);
            return signInResponse;
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid requests");
        }
    }
}
