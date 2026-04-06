package com.feedback.service;

import com.feedback.dto.request.FormCreateRequest;
import com.feedback.dto.response.FormResponse;
import com.feedback.model.Form;
import com.feedback.model.FormFaculty;
import com.feedback.model.FormQuestion;
import com.feedback.repository.FormFacultyRepository;
import com.feedback.repository.FormQuestionRepository;
import com.feedback.repository.FormRepository;
import com.feedback.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormService {
    
    private final FormRepository formRepository;
    private final FormQuestionRepository formQuestionRepository;
    private final FormFacultyRepository formFacultyRepository;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    
    // Constructor injection
    public FormService(FormRepository formRepository, FormQuestionRepository formQuestionRepository,
                       FormFacultyRepository formFacultyRepository, UserRepository userRepository,
                       ObjectMapper objectMapper) {
        this.formRepository = formRepository;
        this.formQuestionRepository = formQuestionRepository;
        this.formFacultyRepository = formFacultyRepository;
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }
    
    @Transactional
    public FormResponse createForm(FormCreateRequest request, String createdBy) {
        // Create form
        Form form = new Form();
        form.setCourse(request.getCourse());
        form.setTitle(request.getTitle());
        form.setSubject(request.getSubject());
        form.setDescription(request.getDescription());
        form.setInstructor(request.getInstructor());
        form.setCreatedBy(createdBy);
        form.setPublished(request.getPublished() != null ? request.getPublished() : true);
        form.setRatingScaleMax(request.getRatingScaleMax() != null ? request.getRatingScaleMax() : 5);
        form.setTemplateKey(request.getTemplateKey());
        
        Form savedForm = formRepository.save(form);
        
        // Create questions
        if (request.getQuestions() != null) {
            for (int i = 0; i < request.getQuestions().size(); i++) {
                FormCreateRequest.QuestionDto q = request.getQuestions().get(i);
                FormQuestion question = new FormQuestion();
                question.setFormId(savedForm.getId());
                question.setQuestionText(q.getQuestion());
                question.setQuestionType(FormQuestion.QuestionType.valueOf(q.getType()));
                question.setOptions(q.getOptions());
                question.setDisplayOrder(i);
                formQuestionRepository.save(question);
            }
        }
        
        // Assign faculty
        if (request.getAssignedFacultyEmails() != null) {
            for (String facultyEmail : request.getAssignedFacultyEmails()) {
                if (userRepository.existsByEmail(facultyEmail)) {
                    FormFaculty assignment = new FormFaculty();
                    assignment.setFormId(savedForm.getId());
                    assignment.setFacultyEmail(facultyEmail);
                    formFacultyRepository.save(assignment);
                }
            }
        }
        
        return convertToResponse(savedForm);
    }
    
    public List<FormResponse> getAllForms() {
        return formRepository.findAll().stream()
            .map(this::convertToResponse)
            .collect(Collectors.toList());
    }
    
    public List<FormResponse> getPublishedForms() {
        return formRepository.findByPublishedTrue().stream()
            .map(this::convertToResponse)
            .collect(Collectors.toList());
    }
    
    public List<FormResponse> getFormsByCreator(String email) {
        return formRepository.findByCreatedBy(email).stream()
            .map(this::convertToResponse)
            .collect(Collectors.toList());
    }
    
    public List<FormResponse> getFormsAssignedToFaculty(String facultyEmail) {
        return formRepository.findFormsAssignedToFaculty(facultyEmail).stream()
            .map(this::convertToResponse)
            .collect(Collectors.toList());
    }
    
    public FormResponse getFormById(Long id) {
        Form form = formRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Form not found"));
        return convertToResponse(form);
    }
    
    @Transactional
    public void deleteForm(Long id) {
        formQuestionRepository.deleteByFormId(id);
        formFacultyRepository.deleteByFormId(id);
        formRepository.deleteById(id);
    }
    
    private FormResponse convertToResponse(Form form) {
        List<FormQuestion> questions = formQuestionRepository.findByFormIdOrderByDisplayOrderAsc(form.getId());
        List<String> assignedFaculty = formFacultyRepository.findByFormId(form.getId()).stream()
            .map(FormFaculty::getFacultyEmail)
            .collect(Collectors.toList());
        
        return FormResponse.builder()
            .id(form.getId())
            .title(form.getTitle())
            .course(form.getCourse())
            .subject(form.getSubject())
            .description(form.getDescription())
            .instructor(form.getInstructor())
            .createdBy(form.getCreatedBy())
            .published(form.getPublished())
            .ratingScaleMax(form.getRatingScaleMax())
            .templateKey(form.getTemplateKey())
            .createdAt(form.getCreatedAt())
            .assignedFacultyEmails(assignedFaculty)
            .questions(questions.stream().map(q -> FormResponse.QuestionDto.builder()
                .id(q.getId())
                .question(q.getQuestionText())
                .type(q.getQuestionType().toString())
                .options(q.getOptions())
                .displayOrder(q.getDisplayOrder())
                .build())
                .collect(Collectors.toList()))
            .build();
    }
}