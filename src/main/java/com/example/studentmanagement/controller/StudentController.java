package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.StudentRequest;
import com.example.studentmanagement.dto.StudentResponse;
import com.example.studentmanagement.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/students")
@Tag(name = "Student Management", description = "APIs for managing student records")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // ─────────────────────────────────────────────────────────────────────────
    // CREATE
    // ─────────────────────────────────────────────────────────────────────────

    @PostMapping
    @Operation(summary = "Add a new student", description = "Creates a new student record")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Student created successfully"),
        @ApiResponse(responseCode = "400", description = "Validation error or email already exists")
    })
    public ResponseEntity<StudentResponse> addStudent(
            @Valid @RequestBody StudentRequest request) {
        StudentResponse response = studentService.addStudent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // READ (single)
    // ─────────────────────────────────────────────────────────────────────────

    @GetMapping("/{id}")
    @Operation(summary = "Get student by ID", description = "Returns a single student by their ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Student found"),
        @ApiResponse(responseCode = "404", description = "Student not found")
    })
    public ResponseEntity<StudentResponse> getStudentById(
            @Parameter(description = "Student ID") @PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    // ─────────────────────────────────────────────────────────────────────────
    // READ (all with pagination + sorting)
    // ─────────────────────────────────────────────────────────────────────────

    @GetMapping
    @Operation(summary = "Get all students",
               description = "Returns paginated list of all students with optional sorting")
    public ResponseEntity<Page<StudentResponse>> getAllStudents(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0")   int page,
            @Parameter(description = "Records per page")      @RequestParam(defaultValue = "10")  int size,
            @Parameter(description = "Sort field")            @RequestParam(defaultValue = "id")  String sortBy,
            @Parameter(description = "asc or desc")          @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(studentService.getAllStudents(page, size, sortBy, direction));
    }

    // ─────────────────────────────────────────────────────────────────────────
    // UPDATE
    // ─────────────────────────────────────────────────────────────────────────

    @PutMapping("/{id}")
    @Operation(summary = "Update student", description = "Updates an existing student record by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Student updated successfully"),
        @ApiResponse(responseCode = "404", description = "Student not found"),
        @ApiResponse(responseCode = "400", description = "Validation error or email conflict")
    })
    public ResponseEntity<StudentResponse> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequest request) {
        return ResponseEntity.ok(studentService.updateStudent(id, request));
    }

    // ─────────────────────────────────────────────────────────────────────────
    // DELETE
    // ─────────────────────────────────────────────────────────────────────────

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete student", description = "Deletes a student record by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Student deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Student not found")
    })
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    // ─────────────────────────────────────────────────────────────────────────
    // SEARCH
    // ─────────────────────────────────────────────────────────────────────────

    @GetMapping("/search/name")
    @Operation(summary = "Search students by name", description = "Case-insensitive partial name search")
    public ResponseEntity<Page<StudentResponse>> searchByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "0")    int page,
            @RequestParam(defaultValue = "10")   int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc")  String direction) {
        return ResponseEntity.ok(studentService.searchByName(name, page, size, sortBy, direction));
    }

    @GetMapping("/search/department")
    @Operation(summary = "Search students by department")
    public ResponseEntity<Page<StudentResponse>> searchByDepartment(
            @RequestParam String department,
            @RequestParam(defaultValue = "0")          int page,
            @RequestParam(defaultValue = "10")         int size,
            @RequestParam(defaultValue = "department") String sortBy,
            @RequestParam(defaultValue = "asc")        String direction) {
        return ResponseEntity.ok(studentService.searchByDepartment(department, page, size, sortBy, direction));
    }

    @GetMapping("/search/semester")
    @Operation(summary = "Search students by semester")
    public ResponseEntity<Page<StudentResponse>> searchBySemester(
            @RequestParam Integer semester,
            @RequestParam(defaultValue = "0")        int page,
            @RequestParam(defaultValue = "10")       int size,
            @RequestParam(defaultValue = "semester") String sortBy,
            @RequestParam(defaultValue = "asc")      String direction) {
        return ResponseEntity.ok(studentService.searchBySemester(semester, page, size, sortBy, direction));
    }

    @GetMapping("/search/cgpa")
    @Operation(summary = "Search students by minimum CGPA",
               description = "Returns students with CGPA >= specified value")
    public ResponseEntity<Page<StudentResponse>> searchByCgpa(
            @RequestParam Double minCgpa,
            @RequestParam(defaultValue = "0")    int page,
            @RequestParam(defaultValue = "10")   int size,
            @RequestParam(defaultValue = "cgpa") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {
        return ResponseEntity.ok(studentService.searchByCgpa(minCgpa, page, size, sortBy, direction));
    }

    @GetMapping("/search/cgpa-range")
    @Operation(summary = "Search students by CGPA range",
               description = "Returns students with CGPA between minCgpa and maxCgpa")
    public ResponseEntity<Page<StudentResponse>> searchByCgpaRange(
            @RequestParam Double minCgpa,
            @RequestParam Double maxCgpa,
            @RequestParam(defaultValue = "0")    int page,
            @RequestParam(defaultValue = "10")   int size,
            @RequestParam(defaultValue = "cgpa") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {
        return ResponseEntity.ok(studentService.searchByCgpaRange(minCgpa, maxCgpa, page, size, sortBy, direction));
    }

    @GetMapping("/search/email")
    @Operation(summary = "Search student by email (exact match)")
    public ResponseEntity<StudentResponse> searchByEmail(@RequestParam String email) {
        return ResponseEntity.ok(studentService.searchByEmail(email));
    }

    @GetMapping("/search/keyword")
    @Operation(summary = "Full-text keyword search",
               description = "Searches across name, email, and department fields")
    public ResponseEntity<Page<StudentResponse>> searchByKeyword(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0")    int page,
            @RequestParam(defaultValue = "10")   int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc")  String direction) {
        return ResponseEntity.ok(studentService.searchByKeyword(keyword, page, size, sortBy, direction));
    }
}
