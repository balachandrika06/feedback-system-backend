package com.feedback.repository;

import com.feedback.model.FormAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FormAnalyticsRepository extends JpaRepository<FormAnalytics, Long> {
    Optional<FormAnalytics> findByFormId(Long formId);
}