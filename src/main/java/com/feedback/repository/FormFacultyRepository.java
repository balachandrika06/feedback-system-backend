package com.feedback.repository;

import com.feedback.model.FormFaculty;
import com.feedback.model.FormFacultyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FormFacultyRepository extends JpaRepository<FormFaculty, FormFacultyId> {
    List<FormFaculty> findByFormId(Long formId);
    List<FormFaculty> findByFacultyEmail(String facultyEmail);
    void deleteByFormId(Long formId);
    void deleteByFacultyEmail(String facultyEmail);
}