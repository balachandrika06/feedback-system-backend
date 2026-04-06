package com.feedback.dto.request;

import java.util.Map;

public class ResponseSubmitRequest {
    private Long formId;
    private Map<Integer, Object> answers;
    
    // Default constructor
    public ResponseSubmitRequest() {
    }
    
    // All args constructor
    public ResponseSubmitRequest(Long formId, Map<Integer, Object> answers) {
        this.formId = formId;
        this.answers = answers;
    }
    
    // Getters and Setters
    public Long getFormId() {
        return formId;
    }
    
    public void setFormId(Long formId) {
        this.formId = formId;
    }
    
    public Map<Integer, Object> getAnswers() {
        return answers;
    }
    
    public void setAnswers(Map<Integer, Object> answers) {
        this.answers = answers;
    }
}