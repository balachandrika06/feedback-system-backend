package com.feedback.service;

import com.feedback.dto.request.LoginRequest;
import com.feedback.dto.request.RegisterRequest;
import com.feedback.dto.response.AuthResponse;
import com.feedback.model.User;
import com.feedback.repository.UserRepository;
import com.feedback.config.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    
    // Constructor injection
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       JwtService jwtService, AuthenticationManager authenticationManager,
                       UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }
    
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }
        
        // Check register number if provided
        if (request.getRegisterNo() != null && userRepository.existsByRegisterNo(request.getRegisterNo())) {
            throw new RuntimeException("Register number already exists");
        }
        
        // Create new user
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail().toLowerCase());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(User.Role.valueOf(request.getRole()));
        user.setRegisterNo(request.getRegisterNo());
        user.setDepartment(request.getDepartment());
        
        userRepository.save(user);
        
        // Generate token
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        String token = jwtService.generateToken(userDetails);
        
        return AuthResponse.builder()
            .token(token)
            .email(user.getEmail())
            .name(user.getName())
            .role(user.getRole().toString())
            .registerNo(user.getRegisterNo())
            .department(user.getDepartment())
            .build();
    }
    
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        String token = jwtService.generateToken(userDetails);
        
        return AuthResponse.builder()
            .token(token)
            .email(user.getEmail())
            .name(user.getName())
            .role(user.getRole().toString())
            .registerNo(user.getRegisterNo())
            .department(user.getDepartment())
            .build();
    }
}