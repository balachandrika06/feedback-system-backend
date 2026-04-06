package com.feedback.controller;

import com.feedback.dto.request.ResponseSubmitRequest;
import com.feedback.dto.response.ApiResponse;
import com.feedback.model.Response;
import com.feedback.service.ResponseService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/responses")
@CrossOrigin(origins = "http://localhost:5173")
public class ResponseController {
    
    private final ResponseService responseService;
    
    // Constructor injection
    public ResponseController(ResponseService responseService) {
        this.responseService = responseService;
    }
    
    @PostMapping
    public ApiResponse<Response> submitResponse(
        @Valid @RequestBody ResponseSubmitRequest request,
        @AuthenticationPrincipal UserDetails userDetails
    ) {
        Response response = responseService.submitResponse(request, userDetails.getUsername());
        return ApiResponse.success("Response submitted successfully", response);
    }
    
    @GetMapping("/form/{formId}")
    public ApiResponse<List<Response>> getResponsesByForm(@PathVariable Long formId) {
        return ApiResponse.success(responseService.getResponsesByForm(formId));
    }
    
    @GetMapping("/my-submissions")
    public ApiResponse<List<Response>> getMySubmissions(@AuthenticationPrincipal UserDetails userDetails) {
        return ApiResponse.success(responseService.getResponsesByStudent(userDetails.getUsername()));
    }
    
    @GetMapping("/check/{formId}")
    public ApiResponse<Boolean> hasSubmitted(
        @PathVariable Long formId,
        @AuthenticationPrincipal UserDetails userDetails
    ) {
        boolean submitted = responseService.hasSubmitted(formId, userDetails.getUsername());
        return ApiResponse.success(submitted);
    }
}