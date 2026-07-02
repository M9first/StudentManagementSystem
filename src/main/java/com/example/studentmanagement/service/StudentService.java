package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.StudentRequest;
import com.example.studentmanagement.dto.StudentResponse;
import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.exception.StudentNotFoundException;
import com.example.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // ─────────────────────────────────────────────────────────────────────────
    // CREATE
    // ─────────────────────────────────────────────────────────────────────────

    public StudentResponse addStudent(StudentRequest request) {
        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException(
                    "Email already registered: " + request.getEmail());
        }
        Student student = mapToEntity(request);
        Student saved = studentRepository.save(student);
        return mapToResponse(saved);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // READ (single)
    // ─────────────────────────────────────────────────────────────────────────

    @Transactional(readOnly = true)
    public StudentResponse getStudentById(Long id) {
        Student student = findByIdOrThrow(id);
        return mapToResponse(student);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // READ (all with pagination & sorting)
    // ─────────────────────────────────────────────────────────────────────────

    @Transactional(readOnly = true)
    public Page<StudentResponse> getAllStudents(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return studentRepository.findAll(pageable).map(this::mapToResponse);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // UPDATE
    // ─────────────────────────────────────────────────────────────────────────

    public StudentResponse updateStudent(Long id, StudentRequest request) {
        Student student = findByIdOrThrow(id);

        if (studentRepository.existsByEmailAndIdNot(request.getEmail(), id)) {
            throw new IllegalArgumentException(
                    "Email already in use by another student: " + request.getEmail());
        }

        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setDepartment(request.getDepartment());
        student.setSemester(request.getSemester());
        student.setCgpa(request.getCgpa());
        student.setPhoneNumber(request.getPhoneNumber());
        student.setAddress(request.getAddress());

        Student updated = studentRepository.save(student);
        return mapToResponse(updated);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // DELETE
    // ─────────────────────────────────────────────────────────────────────────

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        studentRepository.deleteById(id);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // SEARCH
    // ─────────────────────────────────────────────────────────────────────────

    @Transactional(readOnly = true)
    public Page<StudentResponse> searchByName(String name, int page, int size,
                                              String sortBy, String direction) {
        Pageable pageable = buildPageable(page, size, sortBy, direction);
        return studentRepository
                .findByNameContainingIgnoreCase(name, pageable)
                .map(this::mapToResponse);
    }

    @Transactional(readOnly = true)
    public Page<StudentResponse> searchByDepartment(String department, int page, int size,
                                                    String sortBy, String direction) {
        Pageable pageable = buildPageable(page, size, sortBy, direction);
        return studentRepository
                .findByDepartmentContainingIgnoreCase(department, pageable)
                .map(this::mapToResponse);
    }

    @Transactional(readOnly = true)
    public Page<StudentResponse> searchBySemester(Integer semester, int page, int size,
                                                  String sortBy, String direction) {
        Pageable pageable = buildPageable(page, size, sortBy, direction);
        return studentRepository
                .findBySemester(semester, pageable)
                .map(this::mapToResponse);
    }

    @Transactional(readOnly = true)
    public Page<StudentResponse> searchByCgpa(Double minCgpa, int page, int size,
                                              String sortBy, String direction) {
        Pageable pageable = buildPageable(page, size, sortBy, direction);
        return studentRepository
                .findByCgpaGreaterThanEqual(minCgpa, pageable)
                .map(this::mapToResponse);
    }

    @Transactional(readOnly = true)
    public StudentResponse searchByEmail(String email) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with email: " + email));
        return mapToResponse(student);
    }

    @Transactional(readOnly = true)
    public Page<StudentResponse> searchByCgpaRange(Double minCgpa, Double maxCgpa,
                                                   int page, int size,
                                                   String sortBy, String direction) {
        if (minCgpa > maxCgpa) {
            throw new IllegalArgumentException(
                    "minCgpa must be less than or equal to maxCgpa");
        }
        Pageable pageable = buildPageable(page, size, sortBy, direction);
        return studentRepository
                .findByCgpaBetween(minCgpa, maxCgpa, pageable)
                .map(this::mapToResponse);
    }

    @Transactional(readOnly = true)
    public Page<StudentResponse> searchByKeyword(String keyword, int page, int size,
                                                 String sortBy, String direction) {
        Pageable pageable = buildPageable(page, size, sortBy, direction);
        return studentRepository
                .searchByKeyword(keyword, pageable)
                .map(this::mapToResponse);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // HELPERS
    // ─────────────────────────────────────────────────────────────────────────

    private Student findByIdOrThrow(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    private Pageable buildPageable(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        return PageRequest.of(page, size, sort);
    }

    private Student mapToEntity(StudentRequest request) {
        Student student = new Student();
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setDepartment(request.getDepartment());
        student.setSemester(request.getSemester());
        student.setCgpa(request.getCgpa());
        student.setPhoneNumber(request.getPhoneNumber());
        student.setAddress(request.getAddress());
        return student;
    }

    private StudentResponse mapToResponse(Student student) {
        StudentResponse response = new StudentResponse();
        response.setId(student.getId());
        response.setName(student.getName());
        response.setEmail(student.getEmail());
        response.setDepartment(student.getDepartment());
        response.setSemester(student.getSemester());
        response.setCgpa(student.getCgpa());
        response.setPhoneNumber(student.getPhoneNumber());
        response.setAddress(student.getAddress());
        return response;
    }
}
