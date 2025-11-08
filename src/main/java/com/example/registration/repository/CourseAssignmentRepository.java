package com.example.registration.repository;

import com.example.registration.domain.Course;
import com.example.registration.domain.CourseAssignment;
import com.example.registration.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseAssignmentRepository extends JpaRepository<CourseAssignment, Long> {
    
    List<CourseAssignment> findByInstructor(User instructor);
    
    List<CourseAssignment> findByInstructorId(Long instructorId);
    
    List<CourseAssignment> findByCourse(Course course);
    
    Optional<CourseAssignment> findByCourseAndInstructor(Course course, User instructor);
    
    boolean existsByCourseAndInstructor(Course course, User instructor);
    
    boolean existsByInstructor(User instructor);
    
    void deleteByCourse(Course course);
    
    List<CourseAssignment> findByActiveTrue();
    
    List<CourseAssignment> findByInstructorIdAndActiveTrue(Long instructorId);
}
