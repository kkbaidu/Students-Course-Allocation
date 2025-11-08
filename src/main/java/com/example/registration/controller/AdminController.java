package com.example.registration.controller;

import com.example.registration.domain.Semester;
import com.example.registration.dto.*;
import com.example.registration.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Tag(name = "Admin", description = "Admin management endpoints for programmes, courses, students, instructors, and registration control")
public class AdminController {
    
    private final AdminService adminService;
    
    // Programme Management
    @PostMapping("/programmes")
    public ResponseEntity<ProgrammeDto> createProgramme(@Valid @RequestBody ProgrammeDto dto) {
        ProgrammeDto created = adminService.createProgramme(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @PutMapping("/programmes/{id}")
    @Operation(summary = "Update programme", description = "Update an existing programme by ID")
    public ResponseEntity<ProgrammeDto> updateProgramme(
            @PathVariable Long id, 
            @Valid @RequestBody ProgrammeDto dto) {
        ProgrammeDto updated = adminService.updateProgramme(id, dto);
        return ResponseEntity.ok(updated);
    }
    
    @GetMapping("/programmes")
    public ResponseEntity<List<ProgrammeDto>> getAllProgrammes() {
        List<ProgrammeDto> programmes = adminService.getAllProgrammes();
        return ResponseEntity.ok(programmes);
    }
    
    @GetMapping("/programmes/{id}")
    public ResponseEntity<ProgrammeDto> getProgrammeById(@PathVariable Long id) {
        ProgrammeDto programme = adminService.getProgrammeById(id);
        return ResponseEntity.ok(programme);
    }
    
    // Course Management
    @PostMapping("/courses")
    public ResponseEntity<CourseDto> createCourse(@Valid @RequestBody CourseDto dto) {
        CourseDto created = adminService.createCourse(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @PutMapping("/courses/{id}")
    @Operation(summary = "Update course", description = "Update an existing course by ID")
    public ResponseEntity<CourseDto> updateCourse(
            @PathVariable Long id, 
            @Valid @RequestBody CourseDto dto) {
        CourseDto updated = adminService.updateCourse(id, dto);
        return ResponseEntity.ok(updated);
    }
    
    @GetMapping("/courses")
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        List<CourseDto> courses = adminService.getAllCourses();
        return ResponseEntity.ok(courses);
    }
    
    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Long id) {
        CourseDto course = adminService.getCourseById(id);
        return ResponseEntity.ok(course);
    }
    
    // Course Assignment
    @PostMapping("/courses/{id}/assign-instructor")
    public ResponseEntity<String> assignInstructorToCourse(
            @PathVariable Long id,
            @Valid @RequestBody AssignInstructorDto dto) {
        String message = adminService.assignInstructorToCourse(id, dto);
        return ResponseEntity.ok(message);
    }
    
    // Student Management
    @PostMapping("/students")
    @Operation(summary = "Create a new student account", description = "Admin creates a new student user with programme enrollment")
    public ResponseEntity<StudentDto> createStudent(@Valid @RequestBody CreateStudentDto dto) {
        StudentDto created = adminService.createStudent(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @PutMapping("/students/{id}")
    @Operation(summary = "Update student", description = "Update an existing student account by ID")
    public ResponseEntity<StudentDto> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody CreateStudentDto dto) {
        StudentDto updated = adminService.updateStudent(id, dto);
        return ResponseEntity.ok(updated);
    }
    
    @GetMapping("/students")
    @Operation(summary = "Get all students", description = "Retrieve list of all registered students")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> students = adminService.getAllStudents();
        return ResponseEntity.ok(students);
    }
    
    // Instructor Management
    @PostMapping("/instructors")
    @Operation(summary = "Create a new instructor account", description = "Admin creates a new instructor/lecturer user account")
    public ResponseEntity<InstructorDto> createInstructor(@Valid @RequestBody CreateInstructorDto dto) {
        InstructorDto created = adminService.createInstructor(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @PutMapping("/instructors/{id}")
    @Operation(summary = "Update instructor", description = "Update an existing instructor account by ID")
    public ResponseEntity<InstructorDto> updateInstructor(
            @PathVariable Long id,
            @Valid @RequestBody CreateInstructorDto dto) {
        InstructorDto updated = adminService.updateInstructor(id, dto);
        return ResponseEntity.ok(updated);
    }
    
    @GetMapping("/instructors")
    @Operation(summary = "Get all instructors", description = "Retrieve list of all instructors/lecturers")
    public ResponseEntity<List<InstructorDto>> getAllInstructors() {
        List<InstructorDto> instructors = adminService.getAllInstructors();
        return ResponseEntity.ok(instructors);
    }
    
    @GetMapping("/instructors/{id}")
    @Operation(summary = "Get instructor by ID", description = "Retrieve specific instructor details by ID")
    public ResponseEntity<InstructorDto> getInstructorById(@PathVariable Long id) {
        InstructorDto instructor = adminService.getInstructorById(id);
        return ResponseEntity.ok(instructor);
    }
    
    // Registration Management
    @PostMapping("/registration/open")
    public ResponseEntity<String> openRegistration(@Valid @RequestBody RegistrationStatusDto dto) {
        String message = adminService.openRegistration(dto);
        return ResponseEntity.ok(message);
    }
    
    @PostMapping("/registration/close")
    public ResponseEntity<String> closeRegistration(
            @RequestParam Semester semester,
            @RequestParam Integer academicYear) {
        String message = adminService.closeRegistration(semester, academicYear);
        return ResponseEntity.ok(message);
    }
    
    // Reports
    @GetMapping("/courses/{id}/students")
    public ResponseEntity<List<StudentDto>> getStudentsForCourse(@PathVariable Long id) {
        List<StudentDto> students = adminService.getStudentsForCourse(id);
        return ResponseEntity.ok(students);
    }
}
