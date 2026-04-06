package com.feedback.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "form_faculty")
@IdClass(FormFacultyId.class)
public class FormFaculty {
    
    @Id
    @Column(name = "form_id")
    private Long formId;
    
    @Id
    @Column(name = "faculty_email")
    private String facultyEmail;
    
    @Column(name = "assigned_at", updatable = false)
    private LocalDateTime assignedAt;
    
    // Default constructor
    public FormFaculty() {
    }
    
    // All args constructor
    public FormFaculty(Long formId, String facultyEmail, LocalDateTime assignedAt) {
        this.formId = formId;
        this.facultyEmail = facultyEmail;
        this.assignedAt = assignedAt;
    }
    
    @PrePersist
    protected void onCreate() {
        assignedAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getFormId() {
        return formId;
    }
    
    public void setFormId(Long formId) {
        this.formId = formId;
    }
    
    public String getFacultyEmail() {
        return facultyEmail;
    }
    
    public void setFacultyEmail(String facultyEmail) {
        this.facultyEmail = facultyEmail;
    }
    
    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }
    
    public void setAssignedAt(LocalDateTime assignedAt) {
        this.assignedAt = assignedAt;
    }
}