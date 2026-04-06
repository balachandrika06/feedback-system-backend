package com.feedback.repository;

import com.feedback.model.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {
    
    List<Form> findByPublishedTrue();
    
    List<Form> findByCreatedBy(String createdBy);
    
    @Query("SELECT f FROM Form f WHERE f.id IN (SELECT ff.formId FROM FormFaculty ff WHERE ff.facultyEmail = :facultyEmail)")
    List<Form> findFormsAssignedToFaculty(@Param("facultyEmail") String facultyEmail);
    
    @Query("SELECT f FROM Form f WHERE f.published = true AND f.id IN (SELECT ff.formId FROM FormFaculty ff WHERE ff.facultyEmail = :facultyEmail)")
    List<Form> findPublishedFormsAssignedToFaculty(@Param("facultyEmail") String facultyEmail);
}