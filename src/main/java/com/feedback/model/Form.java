package com.feedback.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "forms")
public class Form {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    
    @Column(nullable = false)
    private String course;
    
    private String subject;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    private String instructor;
    
    @Column(name = "created_by", nullable = false)
    private String createdBy;
    
    private Boolean published = true;
    
    @Column(name = "rating_scale_max")
    private Integer ratingScaleMax = 5;
    
    @Column(name = "template_key")
    private String templateKey;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Default constructor
    public Form() {
    }
    
    // All args constructor
    public Form(Long id, String title, String course, String subject, String description, 
                String instructor, String createdBy, Boolean published, Integer ratingScaleMax, 
                String templateKey, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.course = course;
        this.subject = subject;
        this.description = description;
        this.instructor = instructor;
        this.createdBy = createdBy;
        this.published = published;
        this.ratingScaleMax = ratingScaleMax;
        this.templateKey = templateKey;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
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
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}