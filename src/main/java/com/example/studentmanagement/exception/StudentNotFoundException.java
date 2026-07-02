package com.example.studentmanagement.exception;

public class StudentNotFoundException extends RuntimeException {

    private final Long studentId;

    public StudentNotFoundException(Long studentId) {
        super("Student not found with ID: " + studentId);
        this.studentId = studentId;
    }

    public StudentNotFoundException(String message) {
        super(message);
        this.studentId = null;
    }

    public Long getStudentId() {
        return studentId;
    }
}
