package com.example.registration.service;

import com.example.registration.dto.CourseDto;
import com.example.registration.dto.CourseRegistrationDto;
import com.example.registration.dto.StudentCourseRegisterDto;

import java.util.List;

public interface StudentService {
    
    List<CourseDto> getAvailableCourses(String username);
    
    String registerForCourse(String username, StudentCourseRegisterDto dto);
    
    List<CourseRegistrationDto> getRegisteredCourses(String username);
}
