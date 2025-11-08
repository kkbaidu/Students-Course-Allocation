package com.example.registration.config;

import com.example.registration.domain.*;
import com.example.registration.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class SampleDataLoader implements CommandLineRunner {
    
    private final UserRepository userRepository;
    private final ProgrammeRepository programmeRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final CourseAssignmentRepository assignmentRepository;
    private final RegistrationStatusRepository statusRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() > 0) {
            log.info("Sample data already loaded. Skipping...");
            return;
        }
        
        log.info("Loading sample data...");
        
        // Create Admin User
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setFirstName("Admin");
        admin.setLastName("User");
        admin.setEmail("admin@ug.edu.gh");
        admin.setRole(Role.ADMIN);
        admin.setEnabled(true);
        userRepository.save(admin);
        log.info("Created admin user: username=admin, password=admin123");
        
        // Create Instructor Users
        User instructor1 = new User();
        instructor1.setUsername("dr.mensah");
        instructor1.setPassword(passwordEncoder.encode("instructor123"));
        instructor1.setFirstName("Kwame");
        instructor1.setLastName("Mensah");
        instructor1.setEmail("dr.mensah@ug.edu.gh");
        instructor1.setRole(Role.INSTRUCTOR);
        instructor1.setEnabled(true);
        userRepository.save(instructor1);
        
        User instructor2 = new User();
        instructor2.setUsername("dr.asante");
        instructor2.setPassword(passwordEncoder.encode("instructor123"));
        instructor2.setFirstName("Akua");
        instructor2.setLastName("Asante");
        instructor2.setEmail("dr.asante@ug.edu.gh");
        instructor2.setRole(Role.INSTRUCTOR);
        instructor2.setEnabled(true);
        userRepository.save(instructor2);
        log.info("Created instructor users: dr.mensah, dr.asante (password=instructor123)");
        
        // Create Programmes
        Programme csProgram = new Programme();
        csProgram.setCode("BSC-CS");
        csProgram.setName("BSc Computer Science");
        csProgram.setDescription("Bachelor of Science in Computer Science");
        csProgram.setActive(true);
        programmeRepository.save(csProgram);
        
        Programme itProgram = new Programme();
        itProgram.setCode("BSC-IT");
        itProgram.setName("BSc Information Technology");
        itProgram.setDescription("Bachelor of Science in Information Technology");
        itProgram.setActive(true);
        programmeRepository.save(itProgram);
        log.info("Created programmes: BSC-CS, BSC-IT");
        
        // Create Student Users and Student Records
        User studentUser1 = new User();
        studentUser1.setUsername("student1");
        studentUser1.setPassword(passwordEncoder.encode("student123"));
        studentUser1.setFirstName("Yaw");
        studentUser1.setLastName("Osei");
        studentUser1.setEmail("yaw.osei@st.ug.edu.gh");
        studentUser1.setRole(Role.STUDENT);
        studentUser1.setEnabled(true);
        userRepository.save(studentUser1);
        
        Student student1 = new Student();
        student1.setUser(studentUser1);
        student1.setStudentId("10956789");
        student1.setProgramme(csProgram);
        student1.setLevel(Level.LEVEL200);
        student1.setYearOfAdmission(2023);
        student1.setActive(true);
        studentRepository.save(student1);
        
        User studentUser2 = new User();
        studentUser2.setUsername("student2");
        studentUser2.setPassword(passwordEncoder.encode("student123"));
        studentUser2.setFirstName("Ama");
        studentUser2.setLastName("Boateng");
        studentUser2.setEmail("ama.boateng@st.ug.edu.gh");
        studentUser2.setRole(Role.STUDENT);
        studentUser2.setEnabled(true);
        userRepository.save(studentUser2);
        
        Student student2 = new Student();
        student2.setUser(studentUser2);
        student2.setStudentId("10956790");
        student2.setProgramme(csProgram);
        student2.setLevel(Level.LEVEL200);
        student2.setYearOfAdmission(2023);
        student2.setActive(true);
        studentRepository.save(student2);
        log.info("Created student users: student1, student2 (password=student123)");
        
        // Create Courses
        Course course1 = new Course();
        course1.setCourseCode("DCIT201");
        course1.setCourseName("Programming Fundamentals");
        course1.setDescription("Introduction to programming using Java");
        course1.setCreditHours(3);
        course1.setProgramme(csProgram);
        course1.setLevel(Level.LEVEL200);
        course1.setSemester(Semester.FIRST);
        course1.setActive(true);
        courseRepository.save(course1);
        
        Course course2 = new Course();
        course2.setCourseCode("DCIT203");
        course2.setCourseName("Data Structures and Algorithms");
        course2.setDescription("Introduction to data structures and algorithms");
        course2.setCreditHours(3);
        course2.setProgramme(csProgram);
        course2.setLevel(Level.LEVEL200);
        course2.setSemester(Semester.FIRST);
        course2.setActive(true);
        courseRepository.save(course2);
        
        Course course3 = new Course();
        course3.setCourseCode("DCIT205");
        course3.setCourseName("Computer Architecture");
        course3.setDescription("Introduction to computer organization and architecture");
        course3.setCreditHours(3);
        course3.setProgramme(csProgram);
        course3.setLevel(Level.LEVEL200);
        course3.setSemester(Semester.FIRST);
        course3.setActive(true);
        courseRepository.save(course3);
        
        Course course4 = new Course();
        course4.setCourseCode("DCIT207");
        course4.setCourseName("Database Systems");
        course4.setDescription("Introduction to database management systems");
        course4.setCreditHours(3);
        course4.setProgramme(csProgram);
        course4.setLevel(Level.LEVEL200);
        course4.setSemester(Semester.SECOND);
        course4.setActive(true);
        courseRepository.save(course4);
        log.info("Created courses: DCIT201, DCIT203, DCIT205, DCIT207");
        
        // Assign Instructors to Courses
        CourseAssignment assignment1 = new CourseAssignment();
        assignment1.setCourse(course1);
        assignment1.setInstructor(instructor1);
        assignment1.setAssignedDate(LocalDateTime.now());
        assignment1.setActive(true);
        assignmentRepository.save(assignment1);
        
        CourseAssignment assignment2 = new CourseAssignment();
        assignment2.setCourse(course2);
        assignment2.setInstructor(instructor1);
        assignment2.setAssignedDate(LocalDateTime.now());
        assignment2.setActive(true);
        assignmentRepository.save(assignment2);
        
        CourseAssignment assignment3 = new CourseAssignment();
        assignment3.setCourse(course3);
        assignment3.setInstructor(instructor2);
        assignment3.setAssignedDate(LocalDateTime.now());
        assignment3.setActive(true);
        assignmentRepository.save(assignment3);
        
        CourseAssignment assignment4 = new CourseAssignment();
        assignment4.setCourse(course4);
        assignment4.setInstructor(instructor2);
        assignment4.setAssignedDate(LocalDateTime.now());
        assignment4.setActive(true);
        assignmentRepository.save(assignment4);
        log.info("Assigned instructors to courses");
        
        // Create Registration Status (Open for First Semester 2024/2025)
        RegistrationStatus status = new RegistrationStatus();
        status.setSemester(Semester.FIRST);
        status.setAcademicYear(2024);
        status.setOpen(true);
        status.setOpenedAt(LocalDateTime.now());
        status.setLastModified(LocalDateTime.now());
        statusRepository.save(status);
        log.info("Opened registration for FIRST semester 2024/2025");
        
        log.info("Sample data loaded successfully!");
        log.info("===========================================");
        log.info("Login Credentials:");
        log.info("Admin - username: admin, password: admin123");
        log.info("Instructor - username: dr.mensah, password: instructor123");
        log.info("Instructor - username: dr.asante, password: instructor123");
        log.info("Student - username: student1, password: student123");
        log.info("Student - username: student2, password: student123");
        log.info("===========================================");
    }
}
