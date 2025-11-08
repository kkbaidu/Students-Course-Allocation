package com.example.registration.repository;

import com.example.registration.domain.Programme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgrammeRepository extends JpaRepository<Programme, Long> {
    
    Optional<Programme> findByCode(String code);
    
    boolean existsByCode(String code);
    
    List<Programme> findByActiveTrue();
}
