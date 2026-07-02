package com.example.studentmanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Department is required")
    @Column(nullable = false)
    private String department;

    @NotNull(message = "Semester is required")
    @Min(value = 1, message = "Semester must be at least 1")
    @Max(value = 8, message = "Semester must not exceed 8")
    @Column(nullable = false)
    private Integer semester;

    @NotNull(message = "CGPA is required")
    @DecimalMin(value = "0.0", message = "CGPA must be at least 0.0")
    @DecimalMax(value = "10.0", message = "CGPA must not exceed 10.0")
    @Column(nullable = false)
    private Double cgpa;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid phone number. Must be a valid 10-digit Indian mobile number")
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    // ── Constructors ─────────────────────────────────────────────────────────

    public Student() {}

    public Student(Long id, String name, String email, String department,
                   Integer semester, Double cgpa, String phoneNumber, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.semester = semester;
        this.cgpa = cgpa;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    // ── Getters ──────────────────────────────────────────────────────────────

    public Long getId()           { return id; }
    public String getName()       { return name; }
    public String getEmail()      { return email; }
    public String getDepartment() { return department; }
    public Integer getSemester()  { return semester; }
    public Double getCgpa()       { return cgpa; }
    public String getPhoneNumber(){ return phoneNumber; }
    public String getAddress()    { return address; }

    // ── Setters ──────────────────────────────────────────────────────────────

    public void setId(Long id)                 { this.id = id; }
    public void setName(String name)           { this.name = name; }
    public void setEmail(String email)         { this.email = email; }
    public void setDepartment(String dept)     { this.department = dept; }
    public void setSemester(Integer semester)  { this.semester = semester; }
    public void setCgpa(Double cgpa)           { this.cgpa = cgpa; }
    public void setPhoneNumber(String phone)   { this.phoneNumber = phone; }
    public void setAddress(String address)     { this.address = address; }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }
}
