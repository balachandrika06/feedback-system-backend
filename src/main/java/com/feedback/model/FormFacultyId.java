package com.feedback.model;

import java.io.Serializable;
import java.util.Objects;

public class FormFacultyId implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long formId;
    private String facultyEmail;
    
    // Default constructor
    public FormFacultyId() {
    }
    
    // All args constructor
    public FormFacultyId(Long formId, String facultyEmail) {
        this.formId = formId;
        this.facultyEmail = facultyEmail;
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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormFacultyId that = (FormFacultyId) o;
        return Objects.equals(formId, that.formId) && 
               Objects.equals(facultyEmail, that.facultyEmail);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(formId, facultyEmail);
    }
}