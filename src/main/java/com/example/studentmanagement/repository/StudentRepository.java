package com.example.studentmanagement.repository;

import com.example.studentmanagement.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Search by name (case-insensitive, partial match)
    Page<Student> findByNameContainingIgnoreCase(String name, Pageable pageable);

    // Search by department (case-insensitive, partial match)
    Page<Student> findByDepartmentContainingIgnoreCase(String department, Pageable pageable);

    // Search by semester
    Page<Student> findBySemester(Integer semester, Pageable pageable);

    // Search by CGPA greater than or equal to given value
    Page<Student> findByCgpaGreaterThanEqual(Double cgpa, Pageable pageable);

    // Search by email (exact match)
    Optional<Student> findByEmail(String email);

    // Search by CGPA range
    Page<Student> findByCgpaBetween(Double minCgpa, Double maxCgpa, Pageable pageable);

    // Check if email already exists
    boolean existsByEmail(String email);

    // Check if email exists for a different student (for update)
    boolean existsByEmailAndIdNot(String email, Long id);

    // Full text search across name, email, department
    @Query("SELECT s FROM Student s WHERE " +
           "LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(s.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(s.department) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Student> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

    // Get all students by department (list)
    List<Student> findByDepartmentIgnoreCase(String department);

    // Get all students by semester (list)
    List<Student> findBySemester(Integer semester);
}
