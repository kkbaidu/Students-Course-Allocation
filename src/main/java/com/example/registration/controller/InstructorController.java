package com.example.registration.controller;

import com.example.registration.dto.CourseDto;
import com.example.registration.dto.StudentDto;
import com.example.registration.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructor")
@RequiredArgsConstructor
public class InstructorController {
    
    private final InstructorService instructorService;
    
    @GetMapping("/courses")
    public ResponseEntity<List<CourseDto>> getAssignedCourses(Authentication authentication) {
        String username = authentication.getName();
        List<CourseDto> courses = instructorService.getAssignedCourses(username);
        return ResponseEntity.ok(courses);
    }
    
    @GetMapping("/courses/{id}/students")
    public ResponseEntity<List<StudentDto>> getStudentsForCourse(
            Authentication authentication,
            @PathVariable Long id) {
        String username = authentication.getName();
        List<StudentDto> students = instructorService.getStudentsForCourse(username, id);
        return ResponseEntity.ok(students);
    }
}
