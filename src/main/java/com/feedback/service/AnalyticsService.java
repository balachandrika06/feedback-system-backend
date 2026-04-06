package com.feedback.service;

import com.feedback.dto.response.AnalyticsResponse;
import com.feedback.model.Form;
import com.feedback.model.FormAnalytics;
import com.feedback.model.FormQuestion;
import com.feedback.model.Response;
import com.feedback.repository.FormAnalyticsRepository;
import com.feedback.repository.FormQuestionRepository;
import com.feedback.repository.FormRepository;
import com.feedback.repository.ResponseRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {
    
    private final FormRepository formRepository;
    private final FormQuestionRepository formQuestionRepository;
    private final ResponseRepository responseRepository;
    private final FormAnalyticsRepository formAnalyticsRepository;
    private final ObjectMapper objectMapper;
    
    // Constructor injection
    public AnalyticsService(FormRepository formRepository, FormQuestionRepository formQuestionRepository,
                            ResponseRepository responseRepository, FormAnalyticsRepository formAnalyticsRepository,
                            ObjectMapper objectMapper) {
        this.formRepository = formRepository;
        this.formQuestionRepository = formQuestionRepository;
        this.responseRepository = responseRepository;
        this.formAnalyticsRepository = formAnalyticsRepository;
        this.objectMapper = objectMapper;
    }
    
    @Transactional
    public void updateAnalytics(Long formId) {
        Form form = formRepository.findById(formId).orElse(null);
        if (form == null) return;
        
        List<FormQuestion> questions = formQuestionRepository.findByFormIdOrderByDisplayOrderAsc(formId);
        List<Response> responses = responseRepository.findByFormId(formId);
        
        AnalyticsResponse analytics = computeAnalytics(form, questions, responses);
        
        FormAnalytics formAnalytics = formAnalyticsRepository.findByFormId(formId)
            .orElse(new FormAnalytics());
        
        formAnalytics.setFormId(formId);
        formAnalytics.setTotalSubmissions(analytics.getTotalSubmissions());
        formAnalytics.setOverallRating(analytics.getOverallRating());
        
        try {
            String analyticsJson = objectMapper.writeValueAsString(analytics);
            formAnalytics.setAnalyticsJson(analyticsJson);
        } catch (Exception e) {
            // Handle error
        }
        
        formAnalyticsRepository.save(formAnalytics);
    }
    
    public AnalyticsResponse getAnalytics(Long formId) {
        FormAnalytics formAnalytics = formAnalyticsRepository.findByFormId(formId)
            .orElse(null);
        
        if (formAnalytics != null && formAnalytics.getAnalyticsJson() != null) {
            try {
                return objectMapper.readValue(formAnalytics.getAnalyticsJson(), AnalyticsResponse.class);
            } catch (Exception e) {
                // Fall through to compute fresh
            }
        }
        
        // Compute fresh analytics
        Form form = formRepository.findById(formId).orElse(null);
        if (form == null) return null;
        
        List<FormQuestion> questions = formQuestionRepository.findByFormIdOrderByDisplayOrderAsc(formId);
        List<Response> responses = responseRepository.findByFormId(formId);
        
        return computeAnalytics(form, questions, responses);
    }
    
    public Map<Long, AnalyticsResponse> getAllAnalytics() {
        List<Form> forms = formRepository.findAll();
        Map<Long, AnalyticsResponse> result = new HashMap<>();
        
        for (Form form : forms) {
            result.put(form.getId(), getAnalytics(form.getId()));
        }
        
        return result;
    }
    
    private AnalyticsResponse computeAnalytics(Form form, List<FormQuestion> questions, List<Response> responses) {
        int ratingScaleMax = form.getRatingScaleMax() != null ? form.getRatingScaleMax() : 5;
        
        // Calculate averages per question
        List<AnalyticsResponse.QuestionAverage> questionAverages = new ArrayList<>();
        
        for (int i = 0; i < questions.size(); i++) {
            FormQuestion q = questions.get(i);
            
            if (q.getQuestionType() == FormQuestion.QuestionType.rating) {
                List<Double> values = new ArrayList<>();
                
                for (Response response : responses) {
                    try {
                        Map<Integer, Object> answers = objectMapper.readValue(
                            response.getAnswersJson(), 
                            new TypeReference<Map<Integer, Object>>() {}
                        );
                        Object value = answers.get(i);
                        if (value instanceof Number) {
                            values.add(((Number) value).doubleValue());
                        }
                    } catch (Exception e) {
                        // Skip invalid answers
                    }
                }
                
                double average = values.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
                
                questionAverages.add(AnalyticsResponse.QuestionAverage.builder()
                    .question(q.getQuestionText())
                    .type(q.getQuestionType().toString())
                    .average(Math.round(average * 100.0) / 100.0)
                    .build());
            } else {
                questionAverages.add(AnalyticsResponse.QuestionAverage.builder()
                    .question(q.getQuestionText())
                    .type(q.getQuestionType().toString())
                    .average(null)
                    .build());
            }
        }
        
        // Calculate overall rating
        double overallRating = questionAverages.stream()
            .filter(qa -> qa.getAverage() != null)
            .mapToDouble(AnalyticsResponse.QuestionAverage::getAverage)
            .average()
            .orElse(0.0);
        
        // Calculate distribution
        List<AnalyticsResponse.RatingDistribution> distribution = new ArrayList<>();
        for (int rating = 1; rating <= ratingScaleMax; rating++) {
            final int currentRating = rating;
            int count = 0;
            
            for (Response response : responses) {
                try {
                    Map<Integer, Object> answers = objectMapper.readValue(
                        response.getAnswersJson(),
                        new TypeReference<Map<Integer, Object>>() {}
                    );
                    
                    for (int i = 0; i < questions.size(); i++) {
                        if (questions.get(i).getQuestionType() == FormQuestion.QuestionType.rating) {
                            Object value = answers.get(i);
                            if (value instanceof Number && ((Number) value).intValue() == currentRating) {
                                count++;
                            }
                        }
                    }
                } catch (Exception e) {
                    // Skip
                }
            }
            
            distribution.add(AnalyticsResponse.RatingDistribution.builder()
                .rating(rating + " Star")
                .count(count)
                .build());
        }
        
        return AnalyticsResponse.builder()
            .formId(form.getId())
            .ratingScaleMax(ratingScaleMax)
            .totalSubmissions(responses.size())
            .overallRating(Math.round(overallRating * 100.0) / 100.0)
            .averagePerQuestion(questionAverages)
            .distribution(distribution)
            .lastUpdated(new Date().toString())
            .build();
    }
    
    @Scheduled(fixedDelay = 3600000) // Update every hour
    public void refreshAllAnalytics() {
        List<Form> forms = formRepository.findAll();
        for (Form form : forms) {
            updateAnalytics(form.getId());
        }
    }
}