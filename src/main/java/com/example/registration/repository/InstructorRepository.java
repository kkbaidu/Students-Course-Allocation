package com.example.registration.repository;

import com.example.registration.domain.Instructor;
import com.example.registration.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    Optional<Instructor> findByUser(User user);
    Optional<Instructor> findByUserId(Long userId);
}
