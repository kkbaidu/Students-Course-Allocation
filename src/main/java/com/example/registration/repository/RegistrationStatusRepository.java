package com.example.registration.repository;

import com.example.registration.domain.RegistrationStatus;
import com.example.registration.domain.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrationStatusRepository extends JpaRepository<RegistrationStatus, Long> {
    
    Optional<RegistrationStatus> findBySemesterAndAcademicYear(Semester semester, Integer academicYear);
    
    Optional<RegistrationStatus> findFirstByIsOpenTrue();
}
