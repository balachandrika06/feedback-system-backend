package com.feedback.repository;

import com.feedback.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {
    List<Response> findByFormId(Long formId);
    List<Response> findBySubmittedBy(String submittedBy);
    Optional<Response> findByFormIdAndSubmittedBy(Long formId, String submittedBy);
    boolean existsByFormIdAndSubmittedBy(Long formId, String submittedBy);
    
    @Query("SELECT COUNT(r) FROM Response r WHERE r.formId = :formId")
    long countByFormId(@Param("formId") Long formId);
}