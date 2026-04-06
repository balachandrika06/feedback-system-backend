package com.feedback.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public class FormResponse {
    private Long id;
    private String title;
    private String course;
    private String subject;
    private String description;
    private String instructor;
    private String createdBy;
    private Boolean published;
    private Integer ratingScaleMax;
    private String templateKey;
    private LocalDateTime createdAt;
    private List<String> assignedFacultyEmails;
    private List<QuestionDto> questions;
    
    // Default constructor
    public FormResponse() {
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getCourse() {
        return course;
    }
    
    public void setCourse(String course) {
        this.course = course;
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
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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
    
    // Builder pattern
    public static Builder builder() {
        return new Builder();
    }
    
    public static class Builder {
        private Long id;
        private String title;
        private String course;
        private String subject;
        private String description;
        private String instructor;
        private String createdBy;
        private Boolean published;
        private Integer ratingScaleMax;
        private String templateKey;
        private LocalDateTime createdAt;
        private List<String> assignedFacultyEmails;
        private List<QuestionDto> questions;
        
        public Builder id(Long id) { this.id = id; return this; }
        public Builder title(String title) { this.title = title; return this; }
        public Builder course(String course) { this.course = course; return this; }
        public Builder subject(String subject) { this.subject = subject; return this; }
        public Builder description(String description) { this.description = description; return this; }
        public Builder instructor(String instructor) { this.instructor = instructor; return this; }
        public Builder createdBy(String createdBy) { this.createdBy = createdBy; return this; }
        public Builder published(Boolean published) { this.published = published; return this; }
        public Builder ratingScaleMax(Integer ratingScaleMax) { this.ratingScaleMax = ratingScaleMax; return this; }
        public Builder templateKey(String templateKey) { this.templateKey = templateKey; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public Builder assignedFacultyEmails(List<String> assignedFacultyEmails) { this.assignedFacultyEmails = assignedFacultyEmails; return this; }
        public Builder questions(List<QuestionDto> questions) { this.questions = questions; return this; }
        
        public FormResponse build() {
            FormResponse response = new FormResponse();
            response.id = this.id;
            response.title = this.title;
            response.course = this.course;
            response.subject = this.subject;
            response.description = this.description;
            response.instructor = this.instructor;
            response.createdBy = this.createdBy;
            response.published = this.published;
            response.ratingScaleMax = this.ratingScaleMax;
            response.templateKey = this.templateKey;
            response.createdAt = this.createdAt;
            response.assignedFacultyEmails = this.assignedFacultyEmails;
            response.questions = this.questions;
            return response;
        }
    }
    
    // Inner class for QuestionDto
    public static class QuestionDto {
        private Long id;
        private String question;
        private String type;
        private List<String> options;
        private Integer displayOrder;
        
        // Default constructor
        public QuestionDto() {
        }
        
        // Getters and Setters
        public Long getId() {
            return id;
        }
        
        public void setId(Long id) {
            this.id = id;
        }
        
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
        
        public Integer getDisplayOrder() {
            return displayOrder;
        }
        
        public void setDisplayOrder(Integer displayOrder) {
            this.displayOrder = displayOrder;
        }
        
        // Builder pattern
        public static Builder builder() {
            return new Builder();
        }
        
        public static class Builder {
            private Long id;
            private String question;
            private String type;
            private List<String> options;
            private Integer displayOrder;
            
            public Builder id(Long id) { this.id = id; return this; }
            public Builder question(String question) { this.question = question; return this; }
            public Builder type(String type) { this.type = type; return this; }
            public Builder options(List<String> options) { this.options = options; return this; }
            public Builder displayOrder(Integer displayOrder) { this.displayOrder = displayOrder; return this; }
            
            public QuestionDto build() {
                QuestionDto dto = new QuestionDto();
                dto.id = this.id;
                dto.question = this.question;
                dto.type = this.type;
                dto.options = this.options;
                dto.displayOrder = this.displayOrder;
                return dto;
            }
        }
    }
}