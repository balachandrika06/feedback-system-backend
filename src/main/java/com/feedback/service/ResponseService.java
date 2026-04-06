package com.feedback.service;

import com.feedback.dto.request.ResponseSubmitRequest;
import com.feedback.model.Response;
import com.feedback.repository.FormRepository;
import com.feedback.repository.ResponseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ResponseService {
    
    private final ResponseRepository responseRepository;
    private final FormRepository formRepository;
    private final AnalyticsService analyticsService;
    private final ObjectMapper objectMapper;
    
    // Constructor injection
    public ResponseService(ResponseRepository responseRepository, FormRepository formRepository,
                           AnalyticsService analyticsService, ObjectMapper objectMapper) {
        this.responseRepository = responseRepository;
        this.formRepository = formRepository;
        this.analyticsService = analyticsService;
        this.objectMapper = objectMapper;
    }
    
    @Transactional
    public Response submitResponse(ResponseSubmitRequest request, String submittedBy) {
        // Check if already submitted
        if (responseRepository.existsByFormIdAndSubmittedBy(request.getFormId(), submittedBy)) {
            throw new RuntimeException("You have already submitted feedback for this form");
        }
        
        // Check if form exists and is published
        formRepository.findById(request.getFormId())
            .orElseThrow(() -> new RuntimeException("Form not found"));
        
        // Create response
        Response response = new Response();
        response.setFormId(request.getFormId());
        response.setSubmittedBy(submittedBy);
        
        try {
            String answersJson = objectMapper.writeValueAsString(request.getAnswers());
            response.setAnswersJson(answersJson);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize answers");
        }
        
        Response savedResponse = responseRepository.save(response);
        
        // Update analytics
        analyticsService.updateAnalytics(request.getFormId());
        
        return savedResponse;
    }
    
    public List<Response> getResponsesByForm(Long formId) {
        return responseRepository.findByFormId(formId);
    }
    
    public List<Response> getResponsesByStudent(String email) {
        return responseRepository.findBySubmittedBy(email);
    }
    
    public boolean hasSubmitted(Long formId, String email) {
        return responseRepository.existsByFormIdAndSubmittedBy(formId, email);
    }
}