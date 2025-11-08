package com.example.registration.service.impl;

import com.example.registration.domain.*;
import com.example.registration.dto.CourseDto;
import com.example.registration.dto.CourseRegistrationDto;
import com.example.registration.dto.StudentCourseRegisterDto;
import com.example.registration.exception.DuplicateResourceException;
import com.example.registration.exception.RegistrationClosedException;
import com.example.registration.exception.ResourceNotFoundException;
import com.example.registration.repository.*;
import com.example.registration.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService {
    
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final CourseRegistrationRepository registrationRepository;
    private final RegistrationStatusRepository statusRepository;
    
    @Override
    public List<CourseDto> getAvailableCourses(String username) {
        Student student = getStudentByUsername(username);
        
        // Get current registration status
        RegistrationStatus currentStatus = statusRepository.findFirstByIsOpenTrue()
                .orElseThrow(() -> new RegistrationClosedException("Registration is currently closed"));
        
        // Get courses for student's programme, level, and current semester
        List<Course> courses = courseRepository.findByProgrammeAndLevelAndSemester(
            student.getProgramme(),
            student.getLevel(),
            currentStatus.getSemester()
        );
        
        return courses.stream()
                .map(this::mapToCourseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public String registerForCourse(String username, StudentCourseRegisterDto dto) {
        Student student = getStudentByUsername(username);
        
        // Check if registration is open
        RegistrationStatus currentStatus = statusRepository.findFirstByIsOpenTrue()
                .orElseThrow(() -> new RegistrationClosedException("Registration is currently closed"));
        
        // Get the course
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", dto.getCourseId()));
        
        // Validate course is for student's programme and level
        if (!course.getProgramme().equals(student.getProgramme())) {
            throw new IllegalArgumentException("Course is not for your programme");
        }
        
        if (!course.getLevel().equals(student.getLevel())) {
            throw new IllegalArgumentException("Course is not for your level");
        }
        
        if (!course.getSemester().equals(currentStatus.getSemester())) {
            throw new IllegalArgumentException("Course is not offered in the current semester");
        }
        
        // Check for duplicate registration
        if (registrationRepository.existsByStudentAndCourse(student, course)) {
            throw new DuplicateResourceException("You are already registered for this course");
        }
        
        // Create registration
        CourseRegistration registration = new CourseRegistration();
        registration.setStudent(student);
        registration.setCourse(course);
        registration.setSemester(currentStatus.getSemester());
        registration.setAcademicYear(currentStatus.getAcademicYear());
        registration.setActive(true);
        
        registrationRepository.save(registration);
        
        return "Successfully registered for " + course.getCourseName();
    }
    
    @Override
    public List<CourseRegistrationDto> getRegisteredCourses(String username) {
        Student student = getStudentByUsername(username);
        
        List<CourseRegistration> registrations = registrationRepository.findByStudent(student);
        
        return registrations.stream()
                .map(this::mapToCourseRegistrationDto)
                .collect(Collectors.toList());
    }
    
    // Helper Methods
    private Student getStudentByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        
        return studentRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Student record not found for user"));
    }
    
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
    
    private CourseRegistrationDto mapToCourseRegistrationDto(CourseRegistration registration) {
        CourseRegistrationDto dto = new CourseRegistrationDto();
        dto.setId(registration.getId());
        dto.setCourseCode(registration.getCourse().getCourseCode());
        dto.setCourseName(registration.getCourse().getCourseName());
        dto.setCreditHours(registration.getCourse().getCreditHours());
        dto.setSemester(registration.getSemester());
        dto.setAcademicYear(registration.getAcademicYear());
        dto.setRegistrationDate(registration.getRegistrationDate());
        return dto;
    }
}
