package com.example.registration.repository;

import com.example.registration.domain.Level;
import com.example.registration.domain.Programme;
import com.example.registration.domain.Student;
import com.example.registration.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    Optional<Student> findByStudentId(String studentId);
    
    Optional<Student> findByUser(User user);
    
    Optional<Student> findByUserId(Long userId);
    
    boolean existsByStudentId(String studentId);
    
    List<Student> findByProgramme(Programme programme);
    
    List<Student> findByProgrammeAndLevel(Programme programme, Level level);
    
    List<Student> findByActiveTrue();
}
