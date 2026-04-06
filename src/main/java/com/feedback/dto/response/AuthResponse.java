package com.feedback.dto.response;

public class AuthResponse {
    private String token;
    private String email;
    private String name;
    private String role;
    private String registerNo;
    private String department;
    
    // Default constructor
    public AuthResponse() {
    }
    
    // All args constructor
    public AuthResponse(String token, String email, String name, String role, 
                        String registerNo, String department) {
        this.token = token;
        this.email = email;
        this.name = name;
        this.role = role;
        this.registerNo = registerNo;
        this.department = department;
    }
    
    // Getters and Setters
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getRegisterNo() {
        return registerNo;
    }
    
    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    // Builder pattern
    public static Builder builder() {
        return new Builder();
    }
    
    public static class Builder {
        private String token;
        private String email;
        private String name;
        private String role;
        private String registerNo;
        private String department;
        
        public Builder token(String token) {
            this.token = token;
            return this;
        }
        
        public Builder email(String email) {
            this.email = email;
            return this;
        }
        
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        
        public Builder role(String role) {
            this.role = role;
            return this;
        }
        
        public Builder registerNo(String registerNo) {
            this.registerNo = registerNo;
            return this;
        }
        
        public Builder department(String department) {
            this.department = department;
            return this;
        }
        
        public AuthResponse build() {
            return new AuthResponse(token, email, name, role, registerNo, department);
        }
    }
}