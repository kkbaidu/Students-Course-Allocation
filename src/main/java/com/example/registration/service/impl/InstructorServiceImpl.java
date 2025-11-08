package com.example.registration.service.impl;

import com.example.registration.domain.*;
import com.example.registration.dto.CourseDto;
import com.example.registration.dto.StudentDto;
import com.example.registration.exception.ResourceNotFoundException;
import com.example.registration.exception.UnauthorizedException;
import com.example.registration.repository.*;
import com.example.registration.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InstructorServiceImpl implements InstructorService {
    
    private final UserRepository userRepository;
    private final CourseAssignmentRepository assignmentRepository;
    private final CourseRegistrationRepository registrationRepository;
    
    @Override
    public List<CourseDto> getAssignedCourses(String username) {
        User instructor = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        
        if (instructor.getRole() != Role.INSTRUCTOR) {
            throw new UnauthorizedException("User is not an instructor");
        }
        
        List<CourseAssignment> assignments = assignmentRepository
                .findByInstructorIdAndActiveTrue(instructor.getId());
        
        return assignments.stream()
                .map(assignment -> mapToCourseDto(assignment.getCourse()))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<StudentDto> getStudentsForCourse(String username, Long courseId) {
        User instructor = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        
        if (instructor.getRole() != Role.INSTRUCTOR) {
            throw new UnauthorizedException("User is not an instructor");
        }
        
        // Verify instructor is assigned to this course
        List<CourseAssignment> assignments = assignmentRepository
                .findByInstructorIdAndActiveTrue(instructor.getId());
        
        boolean isAssigned = assignments.stream()
                .anyMatch(assignment -> assignment.getCourse().getId().equals(courseId));
        
        if (!isAssigned) {
            throw new UnauthorizedException("You are not assigned to this course");
        }
        
        // Get students registered for the course
        List<CourseRegistration> registrations = registrationRepository.findByCourseId(courseId);
        
        return registrations.stream()
                .map(registration -> mapToStudentDto(registration.getStudent()))
                .collect(Collectors.toList());
    }
    
    // Helper Methods
    private CourseDto mapToCourseDto(Course course) {
        CourseDto dto = new CourseDto();
        dto.setId(course.getId());
        dto.setCourseCode(course.getCourseCode());
        dto.setCourseName(course.getCourseName());
        dto.setDescription(course.getDescription());
        dto.setCreditHours(course.getCreditHours());
        dto.setProgrammeId(course.getProgramme().getId());
        dto.setProgrammeName(course.getProgramme().getName());
        dto.setLevel(course.getLevel());
        dto.setSemester(course.getSemester());
        dto.setActive(course.isActive());
        return dto;
    }
    
    private StudentDto mapToStudentDto(Student student) {
        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setStudentId(student.getStudentId());
        dto.setFirstName(student.getUser().getFirstName());
        dto.setLastName(student.getUser().getLastName());
        dto.setEmail(student.getUser().getEmail());
        dto.setProgrammeName(student.getProgramme().getName());
        dto.setLevel(student.getLevel());
        dto.setYearOfAdmission(student.getYearOfAdmission());
        dto.setActive(student.isActive());
        return dto;
    }
}
