package com.feedback.service;

import com.feedback.dto.request.FacultyCreateRequest;
import com.feedback.model.User;
import com.feedback.repository.FormFacultyRepository;
import com.feedback.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final FormFacultyRepository formFacultyRepository;
    private final PasswordEncoder passwordEncoder;
    
    // Constructor injection
    public UserService(UserRepository userRepository, FormFacultyRepository formFacultyRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.formFacultyRepository = formFacultyRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public List<User> getFacultyUsers() {
        return userRepository.findByRole(User.Role.faculty);
    }
    
    public List<User> getStudentUsers() {
        return userRepository.findByRole(User.Role.student);
    }
    
    @Transactional
    public User createFaculty(FacultyCreateRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        User faculty = new User();
        faculty.setName(request.getName());
        faculty.setEmail(request.getEmail().toLowerCase());
        faculty.setPassword(passwordEncoder.encode(request.getPassword()));
        faculty.setRole(User.Role.faculty);
        faculty.setDepartment(request.getDepartment());
        faculty.setRegisterNo(generateFacultyRegisterNo());
        
        return userRepository.save(faculty);
    }
    
    @Transactional
    public void deleteFaculty(String email) {
        formFacultyRepository.deleteByFacultyEmail(email);
        userRepository.findByEmail(email).ifPresent(userRepository::delete);
    }
    
    private String generateFacultyRegisterNo() {
        long count = userRepository.findByRole(User.Role.faculty).size() + 1;
        return String.format("FAC%03d", count);
    }
}