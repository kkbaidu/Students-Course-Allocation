package com.example.registration.repository;

import com.example.registration.domain.Course;
import com.example.registration.domain.CourseRegistration;
import com.example.registration.domain.Semester;
import com.example.registration.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Long> {
    
    List<CourseRegistration> findByStudent(Student student);
    
    List<CourseRegistration> findByStudentId(Long studentId);
    
    List<CourseRegistration> findByCourse(Course course);
    
    List<CourseRegistration> findByCourseId(Long courseId);
    
    Optional<CourseRegistration> findByStudentAndCourse(Student student, Course course);
    
    boolean existsByStudentAndCourse(Student student, Course course);
    
    List<CourseRegistration> findByStudentAndSemesterAndAcademicYear(
        Student student, 
        Semester semester, 
        Integer academicYear
    );
    
    List<CourseRegistration> findByCourseAndSemesterAndAcademicYear(
        Course course, 
        Semester semester, 
        Integer academicYear
    );
    
    @Query("SELECT SUM(c.creditHours) FROM CourseRegistration cr " +
           "JOIN cr.course c " +
           "WHERE cr.student = :student " +
           "AND cr.semester = :semester " +
           "AND cr.academicYear = :academicYear " +
           "AND cr.active = true")
    Integer sumCreditHoursByStudentAndSemesterAndYear(
        @Param("student") Student student,
        @Param("semester") Semester semester,
        @Param("academicYear") Integer academicYear
    );
}
