package com.feedback.controller;

import com.feedback.dto.response.ApiResponse;
import com.feedback.dto.response.AnalyticsResponse;
import com.feedback.service.AnalyticsService;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
@CrossOrigin(origins = "http://localhost:5173")
public class AnalyticsController {
    
    private final AnalyticsService analyticsService;
    
    // Constructor injection
    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }
    
    @GetMapping("/form/{formId}")
    public ApiResponse<AnalyticsResponse> getFormAnalytics(@PathVariable Long formId) {
        AnalyticsResponse analytics = analyticsService.getAnalytics(formId);
        return ApiResponse.success(analytics);
    }
    
    @GetMapping("/all")
    public ApiResponse<Map<Long, AnalyticsResponse>> getAllAnalytics() {
        return ApiResponse.success(analyticsService.getAllAnalytics());
    }
    
    @PostMapping("/refresh/{formId}")
    public ApiResponse<Void> refreshAnalytics(@PathVariable Long formId) {
        analyticsService.updateAnalytics(formId);
        return ApiResponse.success("Analytics refreshed", null);
    }
}