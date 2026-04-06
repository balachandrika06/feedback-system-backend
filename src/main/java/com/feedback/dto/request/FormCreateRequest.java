package com.feedback.dto.request;

import java.util.List;

public class FormCreateRequest {
    private String course;
    private String title;
    private String subject;
    private String description;
    private String instructor;
    private Boolean published = true;
    private Integer ratingScaleMax = 5;
    private String templateKey;
    private List<String> assignedFacultyEmails;
    private List<QuestionDto> questions;
    
    // Default constructor
    public FormCreateRequest() {
    }
    
    // Getters and Setters
    public String getCourse() {
        return course;
    }
    
    public void setCourse(String course) {
        this.course = course;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getInstructor() {
        return instructor;
    }
    
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
    
    public Boolean getPublished() {
        return published;
    }
    
    public void setPublished(Boolean published) {
        this.published = published;
    }
    
    public Integer getRatingScaleMax() {
        return ratingScaleMax;
    }
    
    public void setRatingScaleMax(Integer ratingScaleMax) {
        this.ratingScaleMax = ratingScaleMax;
    }
    
    public String getTemplateKey() {
        return templateKey;
    }
    
    public void setTemplateKey(String templateKey) {
        this.templateKey = templateKey;
    }
    
    public List<String> getAssignedFacultyEmails() {
        return assignedFacultyEmails;
    }
    
    public void setAssignedFacultyEmails(List<String> assignedFacultyEmails) {
        this.assignedFacultyEmails = assignedFacultyEmails;
    }
    
    public List<QuestionDto> getQuestions() {
        return questions;
    }
    
    public void setQuestions(List<QuestionDto> questions) {
        this.questions = questions;
    }
    
    // Inner class for QuestionDto
    public static class QuestionDto {
        private String question;
        private String type;
        private List<String> options;
        
        // Default constructor
        public QuestionDto() {
        }
        
        // All args constructor
        public QuestionDto(String question, String type, List<String> options) {
            this.question = question;
            this.type = type;
            this.options = options;
        }
        
        // Getters and Setters
        public String getQuestion() {
            return question;
        }
        
        public void setQuestion(String question) {
            this.question = question;
        }
        
        public String getType() {
            return type;
        }
        
        public void setType(String type) {
            this.type = type;
        }
        
        public List<String> getOptions() {
            return options;
        }
        
        public void setOptions(List<String> options) {
            this.options = options;
        }
    }
}