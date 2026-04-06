package com.feedback.controller;

import com.feedback.dto.request.FormCreateRequest;
import com.feedback.dto.response.ApiResponse;
import com.feedback.dto.response.FormResponse;
import com.feedback.service.FormService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/forms")
@CrossOrigin(origins = "http://localhost:5173")
public class FormController {
    
    private final FormService formService;
    
    // Constructor injection
    public FormController(FormService formService) {
        this.formService = formService;
    }
    
    @PostMapping
    public ApiResponse<FormResponse> createForm(
        @Valid @RequestBody FormCreateRequest request,
        @AuthenticationPrincipal UserDetails userDetails
    ) {
        FormResponse response = formService.createForm(request, userDetails.getUsername());
        return ApiResponse.success("Form created successfully", response);
    }
    
    @GetMapping
    public ApiResponse<List<FormResponse>> getAllForms() {
        return ApiResponse.success(formService.getAllForms());
    }
    
    @GetMapping("/published")
    public ApiResponse<List<FormResponse>> getPublishedForms() {
        return ApiResponse.success(formService.getPublishedForms());
    }
    
    @GetMapping("/my-forms")
    public ApiResponse<List<FormResponse>> getMyForms(@AuthenticationPrincipal UserDetails userDetails) {
        return ApiResponse.success(formService.getFormsByCreator(userDetails.getUsername()));
    }
    
    @GetMapping("/assigned")
    public ApiResponse<List<FormResponse>> getAssignedForms(@AuthenticationPrincipal UserDetails userDetails) {
        return ApiResponse.success(formService.getFormsAssignedToFaculty(userDetails.getUsername()));
    }
    
    @GetMapping("/{id}")
    public ApiResponse<FormResponse> getFormById(@PathVariable Long id) {
        return ApiResponse.success(formService.getFormById(id));
    }
    
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteForm(@PathVariable Long id) {
        formService.deleteForm(id);
        return ApiResponse.success("Form deleted successfully", null);
    }
}