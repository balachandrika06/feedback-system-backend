package com.feedback.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "form_analytics")
public class FormAnalytics {
    
    @Id
    @Column(name = "form_id")
    private Long formId;
    
    @Column(name = "total_submissions")
    private Integer totalSubmissions = 0;
    
    @Column(name = "overall_rating")
    private Double overallRating = 0.0;
    
    @Column(name = "analytics_json", columnDefinition = "JSON")
    private String analyticsJson;
    
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;
    
    // Default constructor
    public FormAnalytics() {
    }
    
    // All args constructor
    public FormAnalytics(Long formId, Integer totalSubmissions, Double overallRating, 
                         String analyticsJson, LocalDateTime lastUpdated) {
        this.formId = formId;
        this.totalSubmissions = totalSubmissions;
        this.overallRating = overallRating;
        this.analyticsJson = analyticsJson;
        this.lastUpdated = lastUpdated;
    }
    
    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        lastUpdated = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getFormId() {
        return formId;
    }
    
    public void setFormId(Long formId) {
        this.formId = formId;
    }
    
    public Integer getTotalSubmissions() {
        return totalSubmissions;
    }
    
    public void setTotalSubmissions(Integer totalSubmissions) {
        this.totalSubmissions = totalSubmissions;
    }
    
    public Double getOverallRating() {
        return overallRating;
    }
    
    public void setOverallRating(Double overallRating) {
        this.overallRating = overallRating;
    }
    
    public String getAnalyticsJson() {
        return analyticsJson;
    }
    
    public void setAnalyticsJson(String analyticsJson) {
        this.analyticsJson = analyticsJson;
    }
    
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
    
    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}