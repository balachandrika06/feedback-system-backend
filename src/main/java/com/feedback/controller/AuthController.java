package com.feedback.controller;

import com.feedback.dto.request.LoginRequest;
import com.feedback.dto.request.RegisterRequest;
import com.feedback.dto.response.ApiResponse;
import com.feedback.dto.response.AuthResponse;
import com.feedback.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {
    
    private final AuthService authService;
    
    // Constructor injection
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    
    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ApiResponse.success("Login successful", response);
    }
    
    @PostMapping("/register")
    public ApiResponse<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ApiResponse.success("Registration successful", response);
    }
}