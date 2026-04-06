package com.feedback.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "form_questions")
public class FormQuestion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "form_id", nullable = false)
    private Long formId;
    
    @Column(name = "question_text", nullable = false, columnDefinition = "TEXT")
    private String questionText;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "question_type", nullable = false)
    private QuestionType questionType;
    
    @Column(name = "options_json", columnDefinition = "JSON")
    private String optionsJson;
    
    @Column(name = "display_order")
    private Integer displayOrder = 0;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    public enum QuestionType {
        rating, single_choice, multi_choice, text
    }
    
    // Default constructor
    public FormQuestion() {
    }
    
    // All args constructor
    public FormQuestion(Long id, Long formId, String questionText, QuestionType questionType, 
                        String optionsJson, Integer displayOrder, LocalDateTime createdAt) {
        this.id = id;
        this.formId = formId;
        this.questionText = questionText;
        this.questionType = questionType;
        this.optionsJson = optionsJson;
        this.displayOrder = displayOrder;
        this.createdAt = createdAt;
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    // Helper methods for options
    public List<String> getOptions() {
        if (optionsJson == null || optionsJson.isEmpty()) {
            return List.of();
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(optionsJson, new TypeReference<List<String>>() {});
        } catch (Exception e) {
            return List.of();
        }
    }
    
    public void setOptions(List<String> options) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            this.optionsJson = mapper.writeValueAsString(options);
        } catch (Exception e) {
            this.optionsJson = "[]";
        }
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
    
    public String getQuestionText() {
        return questionText;
    }
    
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
    
    public QuestionType getQuestionType() {
        return questionType;
    }
    
    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }
    
    public String getOptionsJson() {
        return optionsJson;
    }
    
    public void setOptionsJson(String optionsJson) {
        this.optionsJson = optionsJson;
    }
    
    public Integer getDisplayOrder() {
        return displayOrder;
    }
    
    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}