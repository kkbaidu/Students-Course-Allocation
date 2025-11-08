package com.example.registration.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "registration_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationStatus {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Semester semester;
    
    @Column(nullable = false)
    private Integer academicYear;
    
    @Column(nullable = false)
    private boolean isOpen = false;
    
    private LocalDateTime openedAt;
    
    private LocalDateTime closedAt;
    
    @Column(nullable = false)
    private LocalDateTime lastModified;
    
    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        lastModified = LocalDateTime.now();
    }
}
