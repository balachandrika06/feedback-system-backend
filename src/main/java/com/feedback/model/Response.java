package com.feedback.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "responses", 
       uniqueConstraints = @UniqueConstraint(columnNames = {"form_id", "submitted_by"}))
public class Response {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "form_id", nullable = false)
    private Long formId;
    
    @Column(name = "submitted_by", nullable = false)
    private String submittedBy;
    
    @Column(name = "answers_json", nullable = false, columnDefinition = "JSON")
    private String answersJson;
    
    @Column(name = "submitted_at", updatable = false)
    private LocalDateTime submittedAt;
    
    // Default constructor
    public Response() {
    }
    
    // All args constructor
    public Response(Long id, Long formId, String submittedBy, String answersJson, LocalDateTime submittedAt) {
        this.id = id;
        this.formId = formId;
        this.submittedBy = submittedBy;
        this.answersJson = answersJson;
        this.submittedAt = submittedAt;
    }
    
    @PrePersist
    protected void onCreate() {
        submittedAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getFormId() {
        return formId;
    }
    
    public void setFormId(Long formId) {
        this.formId = formId;
    }
    
    public String getSubmittedBy() {
        return submittedBy;
    }
    
    public void setSubmittedBy(String submittedBy) {
        this.submittedBy = submittedBy;
    }
    
    public String getAnswersJson() {
        return answersJson;
    }
    
    public void setAnswersJson(String answersJson) {
        this.answersJson = answersJson;
    }
    
    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }
    
    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }
}