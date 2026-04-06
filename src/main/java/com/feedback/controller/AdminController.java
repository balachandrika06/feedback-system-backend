package com.feedback.controller;

import com.feedback.dto.request.FacultyCreateRequest;
import com.feedback.dto.response.ApiResponse;
import com.feedback.model.User;
import com.feedback.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:5173")
@PreAuthorize("hasAuthority('admin')")
public class AdminController {
    
    private final UserService userService;
    
    // Constructor injection
    public AdminController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/faculty")
    public ApiResponse<List<User>> getFaculty() {
        return ApiResponse.success(userService.getFacultyUsers());
    }
    
    @PostMapping("/faculty")
    public ApiResponse<User> createFaculty(@Valid @RequestBody FacultyCreateRequest request) {
        User faculty = userService.createFaculty(request);
        return ApiResponse.success("Faculty created successfully", faculty);
    }
    
    @DeleteMapping("/faculty/{email}")
    public ApiResponse<Void> deleteFaculty(@PathVariable String email) {
        userService.deleteFaculty(email);
        return ApiResponse.success("Faculty deleted successfully", null);
    }
}