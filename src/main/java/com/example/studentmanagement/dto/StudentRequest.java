package com.example.studentmanagement.dto;

import jakarta.validation.constraints.*;

public class StudentRequest {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Department is required")
    private String department;

    @NotNull(message = "Semester is required")
    @Min(value = 1, message = "Semester must be at least 1")
    @Max(value = 8, message = "Semester must not exceed 8")
    private Integer semester;

    @NotNull(message = "CGPA is required")
    @DecimalMin(value = "0.0", message = "CGPA must be at least 0.0")
    @DecimalMax(value = "10.0", message = "CGPA must not exceed 10.0")
    private Double cgpa;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid phone number. Must be a valid 10-digit Indian mobile number")
    private String phoneNumber;

    private String address;

    // ── Constructors ─────────────────────────────────────────────────────────

    public StudentRequest() {}

    public StudentRequest(String name, String email, String department,
                          Integer semester, Double cgpa, String phoneNumber, String address) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.semester = semester;
        this.cgpa = cgpa;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    // ── Getters ──────────────────────────────────────────────────────────────

    public String getName()        { return name; }
    public String getEmail()       { return email; }
    public String getDepartment()  { return department; }
    public Integer getSemester()   { return semester; }
    public Double getCgpa()        { return cgpa; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getAddress()     { return address; }

    // ── Setters ──────────────────────────────────────────────────────────────

    public void setName(String name)           { this.name = name; }
    public void setEmail(String email)         { this.email = email; }
    public void setDepartment(String dept)     { this.department = dept; }
    public void setSemester(Integer semester)  { this.semester = semester; }
    public void setCgpa(Double cgpa)           { this.cgpa = cgpa; }
    public void setPhoneNumber(String phone)   { this.phoneNumber = phone; }
    public void setAddress(String address)     { this.address = address; }
}
