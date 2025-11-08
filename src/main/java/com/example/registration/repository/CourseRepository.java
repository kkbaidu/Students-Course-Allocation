package com.example.registration.repository;

import com.example.registration.domain.Course;
import com.example.registration.domain.Level;
import com.example.registration.domain.Programme;
import com.example.registration.domain.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    
    Optional<Course> findByCourseCode(String courseCode);
    
    boolean existsByCourseCode(String courseCode);
    
    List<Course> findByProgramme(Programme programme);
    
    List<Course> findByProgrammeAndLevelAndSemester(
        Programme programme, 
        Level level, 
        Semester semester
    );
    
    List<Course> findByActiveTrue();
}
