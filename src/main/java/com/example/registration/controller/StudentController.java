package com.example.registration.controller;

import com.example.registration.dto.CourseDto;
import com.example.registration.dto.CourseRegistrationDto;
import com.example.registration.dto.StudentCourseRegisterDto;
import com.example.registration.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    
    private final StudentService studentService;
    
    @GetMapping("/courses/available")
    public ResponseEntity<List<CourseDto>> getAvailableCourses(Authentication authentication) {
        String username = authentication.getName();
        List<CourseDto> courses = studentService.getAvailableCourses(username);
        return ResponseEntity.ok(courses);
    }
    
    @PostMapping("/courses/register")
    public ResponseEntity<String> registerForCourse(
            Authentication authentication,
            @Valid @RequestBody StudentCourseRegisterDto dto) {
        String username = authentication.getName();
        String message = studentService.registerForCourse(username, dto);
        return ResponseEntity.ok(message);
    }
    
    @GetMapping("/courses/registered")
    public ResponseEntity<List<CourseRegistrationDto>> getRegisteredCourses(Authentication authentication) {
        String username = authentication.getName();
        List<CourseRegistrationDto> courses = studentService.getRegisteredCourses(username);
        return ResponseEntity.ok(courses);
    }
}
