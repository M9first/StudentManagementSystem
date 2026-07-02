package com.example.studentmanagement.dto;

public class StudentResponse {

    private Long id;
    private String name;
    private String email;
    private String department;
    private Integer semester;
    private Double cgpa;
    private String phoneNumber;
    private String address;

    // ── Constructors ─────────────────────────────────────────────────────────

    public StudentResponse() {}

    public StudentResponse(Long id, String name, String email, String department,
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Integer getSemester() {
		return semester;
	}

	public void setSemester(Integer semester) {
		this.semester = semester;
	}

	public Double getCgpa() {
		return cgpa;
	}

	public void setCgpa(Double cgpa) {
		this.cgpa = cgpa;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

   
}
