package com.example.registration.service;

import com.example.registration.dto.*;
import com.example.registration.domain.Semester;

import java.util.List;

public interface AdminService {
    
    // Programme Management
    ProgrammeDto createProgramme(ProgrammeDto programmeDto);
    ProgrammeDto updateProgramme(Long id, ProgrammeDto programmeDto);
    List<ProgrammeDto> getAllProgrammes();
    ProgrammeDto getProgrammeById(Long id);
    
    // Course Management
    CourseDto createCourse(CourseDto courseDto);
    CourseDto updateCourse(Long id, CourseDto courseDto);
    List<CourseDto> getAllCourses();
    CourseDto getCourseById(Long id);
    
    // Course Assignment
    String assignInstructorToCourse(Long courseId, AssignInstructorDto dto);
    
    // Student Management
    StudentDto createStudent(CreateStudentDto dto);
    StudentDto updateStudent(Long id, CreateStudentDto dto);
    List<StudentDto> getAllStudents();
    
    // Instructor Management
    InstructorDto createInstructor(CreateInstructorDto dto);
    InstructorDto updateInstructor(Long id, CreateInstructorDto dto);
    List<InstructorDto> getAllInstructors();
    InstructorDto getInstructorById(Long id);
    
    // Registration Management
    String openRegistration(RegistrationStatusDto dto);
    String closeRegistration(Semester semester, Integer academicYear);
    
    // Reports
    List<StudentDto> getStudentsForCourse(Long courseId);
}
