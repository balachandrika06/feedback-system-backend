package com.feedback.dto.response;

import java.util.List;

public class AnalyticsResponse {
    private Long formId;
    private Integer ratingScaleMax;
    private Integer totalSubmissions;
    private Double overallRating;
    private List<QuestionAverage> averagePerQuestion;
    private List<RatingDistribution> distribution;
    private String lastUpdated;
    
    // Default constructor
    public AnalyticsResponse() {
    }
    
    // Getters and Setters
    public Long getFormId() {
        return formId;
    }
    
    public void setFormId(Long formId) {
        this.formId = formId;
    }
    
    public Integer getRatingScaleMax() {
        return ratingScaleMax;
    }
    
    public void setRatingScaleMax(Integer ratingScaleMax) {
        this.ratingScaleMax = ratingScaleMax;
    }
    
    public Integer getTotalSubmissions() {
        return totalSubmissions;
    }
    
    public void setTotalSubmissions(Integer totalSubmissions) {
        this.totalSubmissions = totalSubmissions;
    }
    
    public Double getOverallRating() {
        return overallRating;
    }
    
    public void setOverallRating(Double overallRating) {
        this.overallRating = overallRating;
    }
    
    public List<QuestionAverage> getAveragePerQuestion() {
        return averagePerQuestion;
    }
    
    public void setAveragePerQuestion(List<QuestionAverage> averagePerQuestion) {
        this.averagePerQuestion = averagePerQuestion;
    }
    
    public List<RatingDistribution> getDistribution() {
        return distribution;
    }
    
    public void setDistribution(List<RatingDistribution> distribution) {
        this.distribution = distribution;
    }
    
    public String getLastUpdated() {
        return lastUpdated;
    }
    
    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    // Builder pattern
    public static Builder builder() {
        return new Builder();
    }
    
    public static class Builder {
        private Long formId;
        private Integer ratingScaleMax;
        private Integer totalSubmissions;
        private Double overallRating;
        private List<QuestionAverage> averagePerQuestion;
        private List<RatingDistribution> distribution;
        private String lastUpdated;
        
        public Builder formId(Long formId) { this.formId = formId; return this; }
        public Builder ratingScaleMax(Integer ratingScaleMax) { this.ratingScaleMax = ratingScaleMax; return this; }
        public Builder totalSubmissions(Integer totalSubmissions) { this.totalSubmissions = totalSubmissions; return this; }
        public Builder overallRating(Double overallRating) { this.overallRating = overallRating; return this; }
        public Builder averagePerQuestion(List<QuestionAverage> averagePerQuestion) { this.averagePerQuestion = averagePerQuestion; return this; }
        public Builder distribution(List<RatingDistribution> distribution) { this.distribution = distribution; return this; }
        public Builder lastUpdated(String lastUpdated) { this.lastUpdated = lastUpdated; return this; }
        
        public AnalyticsResponse build() {
            AnalyticsResponse response = new AnalyticsResponse();
            response.formId = this.formId;
            response.ratingScaleMax = this.ratingScaleMax;
            response.totalSubmissions = this.totalSubmissions;
            response.overallRating = this.overallRating;
            response.averagePerQuestion = this.averagePerQuestion;
            response.distribution = this.distribution;
            response.lastUpdated = this.lastUpdated;
            return response;
        }
    }
    
    // Inner class for QuestionAverage
    public static class QuestionAverage {
        private String question;
        private String type;
        private Double average;
        
        // Default constructor
        public QuestionAverage() {
        }
        
        // Getters and Setters
        public String getQuestion() {
            return question;
        }
        
        public void setQuestion(String question) {
            this.question = question;
        }
        
        public String getType() {
            return type;
        }
        
        public void setType(String type) {
            this.type = type;
        }
        
        public Double getAverage() {
            return average;
        }
        
        public void setAverage(Double average) {
            this.average = average;
        }
        
        // Builder pattern
        public static Builder builder() {
            return new Builder();
        }
        
        public static class Builder {
            private String question;
            private String type;
            private Double average;
            
            public Builder question(String question) { this.question = question; return this; }
            public Builder type(String type) { this.type = type; return this; }
            public Builder average(Double average) { this.average = average; return this; }
            
            public QuestionAverage build() {
                QuestionAverage qa = new QuestionAverage();
                qa.question = this.question;
                qa.type = this.type;
                qa.average = this.average;
                return qa;
            }
        }
    }
    
    // Inner class for RatingDistribution
    public static class RatingDistribution {
        private String rating;
        private Integer count;
        
        // Default constructor
        public RatingDistribution() {
        }
        
        // Getters and Setters
        public String getRating() {
            return rating;
        }
        
        public void setRating(String rating) {
            this.rating = rating;
        }
        
        public Integer getCount() {
            return count;
        }
        
        public void setCount(Integer count) {
            this.count = count;
        }
        
        // Builder pattern
        public static Builder builder() {
            return new Builder();
        }
        
        public static class Builder {
            private String rating;
            private Integer count;
            
            public Builder rating(String rating) { this.rating = rating; return this; }
            public Builder count(Integer count) { this.count = count; return this; }
            
            public RatingDistribution build() {
                RatingDistribution rd = new RatingDistribution();
                rd.rating = this.rating;
                rd.count = this.count;
                return rd;
            }
        }
    }
}