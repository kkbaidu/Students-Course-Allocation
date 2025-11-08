package com.example.registration.service.impl;

import com.example.registration.domain.*;
import com.example.registration.dto.*;
import com.example.registration.exception.DuplicateResourceException;
import com.example.registration.exception.ResourceNotFoundException;
import com.example.registration.repository.*;
import com.example.registration.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService {
    
    private final ProgrammeRepository programmeRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final InstructorRepository instructorRepository;
    private final CourseAssignmentRepository assignmentRepository;
    private final CourseRegistrationRepository registrationRepository;
    private final RegistrationStatusRepository statusRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public ProgrammeDto createProgramme(ProgrammeDto dto) {
        if (programmeRepository.existsByCode(dto.getCode())) {
            throw new DuplicateResourceException("Programme with code " + dto.getCode() + " already exists");
        }
        
        Programme programme = new Programme();
        programme.setCode(dto.getCode());
        programme.setName(dto.getName());
        programme.setDescription(dto.getDescription());
        programme.setActive(dto.isActive());
        
        Programme saved = programmeRepository.save(programme);
        return mapToProgrammeDto(saved);
    }
    
    @Override
    public List<ProgrammeDto> getAllProgrammes() {
        return programmeRepository.findAll().stream()
                .map(this::mapToProgrammeDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public ProgrammeDto getProgrammeById(Long id) {
        Programme programme = programmeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Programme", "id", id));
        return mapToProgrammeDto(programme);
    }
    
    @Override
    public CourseDto createCourse(CourseDto dto) {
        if (courseRepository.existsByCourseCode(dto.getCourseCode())) {
            throw new DuplicateResourceException("Course with code " + dto.getCourseCode() + " already exists");
        }
        
        Programme programme = programmeRepository.findById(dto.getProgrammeId())
                .orElseThrow(() -> new ResourceNotFoundException("Programme", "id", dto.getProgrammeId()));
        
        Course course = new Course();
        course.setCourseCode(dto.getCourseCode());
        course.setCourseName(dto.getCourseName());
        course.setDescription(dto.getDescription());
        course.setCreditHours(dto.getCreditHours());
        course.setProgramme(programme);
        course.setLevel(dto.getLevel());
        course.setSemester(dto.getSemester());
        course.setActive(dto.isActive());
        
        Course saved = courseRepository.save(course);
        return mapToCourseDto(saved);
    }
    
    @Override
    public List<CourseDto> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(this::mapToCourseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public CourseDto getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));
        return mapToCourseDto(course);
    }
    
    @Override
    public String assignInstructorToCourse(Long courseId, AssignInstructorDto dto) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", courseId));
        
        User instructor = userRepository.findById(dto.getInstructorId())
                .orElseThrow(() -> new ResourceNotFoundException("Instructor", "id", dto.getInstructorId()));
        
        if (instructor.getRole() != Role.INSTRUCTOR) {
            throw new IllegalArgumentException("User is not an instructor");
        }
        
        if (assignmentRepository.existsByCourseAndInstructor(course, instructor)) {
            throw new DuplicateResourceException("Instructor already assigned to this course");
        }
        
        CourseAssignment assignment = new CourseAssignment();
        assignment.setCourse(course);
        assignment.setInstructor(instructor);
        assignment.setActive(true);
        
        assignmentRepository.save(assignment);
        
        return "Instructor successfully assigned to course";
    }
    
    @Override
    public StudentDto createStudent(CreateStudentDto dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new DuplicateResourceException("Username already exists");
        }
        
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }
        
        if (studentRepository.existsByStudentId(dto.getStudentId())) {
            throw new DuplicateResourceException("Student ID already exists");
        }
        
        Programme programme = programmeRepository.findById(dto.getProgrammeId())
                .orElseThrow(() -> new ResourceNotFoundException("Programme", "id", dto.getProgrammeId()));
        
        // Create User
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setRole(Role.STUDENT);
        user.setEnabled(true);
        User savedUser = userRepository.save(user);
        
        // Create Student
        Student student = new Student();
        student.setUser(savedUser);
        student.setStudentId(dto.getStudentId());
        student.setProgramme(programme);
        student.setLevel(dto.getLevel());
        student.setYearOfAdmission(dto.getYearOfAdmission());
        student.setActive(true);
        Student savedStudent = studentRepository.save(student);
        
        return mapToStudentDto(savedStudent);
    }
    
    @Override
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::mapToStudentDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public InstructorDto createInstructor(CreateInstructorDto dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new DuplicateResourceException("Username already exists");
        }
        
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }
        
        // Create User
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setRole(Role.INSTRUCTOR);
        user.setEnabled(true);
        User savedUser = userRepository.save(user);
        
        // Create Instructor
        Instructor instructor = new Instructor();
        instructor.setUser(savedUser);
        instructor.setFullName(dto.getFullName());
        instructor.setDepartment(dto.getDepartment());
        instructor.setTitle(dto.getTitle());
        Instructor savedInstructor = instructorRepository.save(instructor);
        
        return mapToInstructorDto(savedInstructor);
    }
    
    @Override
    public List<InstructorDto> getAllInstructors() {
        return instructorRepository.findAll().stream()
                .map(this::mapToInstructorDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public InstructorDto getInstructorById(Long id) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor", "id", id));
        return mapToInstructorDto(instructor);
    }
    
    @Override
    public String openRegistration(RegistrationStatusDto dto) {
        RegistrationStatus status = statusRepository
                .findBySemesterAndAcademicYear(dto.getSemester(), dto.getAcademicYear())
                .orElse(new RegistrationStatus());
        
        status.setSemester(dto.getSemester());
        status.setAcademicYear(dto.getAcademicYear());
        status.setOpen(true);
        status.setOpenedAt(LocalDateTime.now());
        
        statusRepository.save(status);
        
        return "Registration opened for " + dto.getSemester() + " " + dto.getAcademicYear();
    }
    
    @Override
    public String closeRegistration(Semester semester, Integer academicYear) {
        RegistrationStatus status = statusRepository
                .findBySemesterAndAcademicYear(semester, academicYear)
                .orElseThrow(() -> new ResourceNotFoundException("Registration status not found"));
        
        status.setOpen(false);
        status.setClosedAt(LocalDateTime.now());
        
        statusRepository.save(status);
        
        return "Registration closed for " + semester + " " + academicYear;
    }
    
    @Override
    public List<StudentDto> getStudentsForCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", courseId));
        
        List<CourseRegistration> registrations = registrationRepository.findByCourse(course);
        
        return registrations.stream()
                .map(reg -> mapToStudentDto(reg.getStudent()))
                .collect(Collectors.toList());
    }
    
    // Helper Methods
    private ProgrammeDto mapToProgrammeDto(Programme programme) {
        return new ProgrammeDto(
            programme.getId(),
            programme.getCode(),
            programme.getName(),
            programme.getDescription(),
            programme.isActive()
        );
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
    
    private InstructorDto mapToInstructorDto(Instructor instructor) {
        InstructorDto dto = new InstructorDto();
        dto.setId(instructor.getId());
        dto.setUsername(instructor.getUser().getUsername());
        dto.setEmail(instructor.getUser().getEmail());
        dto.setFullName(instructor.getFullName());
        dto.setDepartment(instructor.getDepartment());
        dto.setTitle(instructor.getTitle());
        dto.setEnabled(instructor.getUser().isEnabled());
        return dto;
    }
}
