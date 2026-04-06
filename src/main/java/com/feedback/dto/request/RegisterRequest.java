package com.feedback.dto.request;

public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String registerNo;
    private String department;
    private String role = "student";
    
    // Default constructor
    public RegisterRequest() {
    }
    
    // All args constructor
    public RegisterRequest(String name, String email, String password, 
                           String registerNo, String department, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.registerNo = registerNo;
        this.department = department;
        this.role = role;
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
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
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
}