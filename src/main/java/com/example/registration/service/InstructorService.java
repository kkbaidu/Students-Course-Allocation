package com.example.registration.service;

import com.example.registration.dto.CourseDto;
import com.example.registration.dto.StudentDto;

import java.util.List;

public interface InstructorService {
    
    List<CourseDto> getAssignedCourses(String username);
    
    List<StudentDto> getStudentsForCourse(String username, Long courseId);
}
