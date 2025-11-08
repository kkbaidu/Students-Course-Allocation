package com.example.registration.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;
    
    @Column(unique = true, nullable = false)
    private String studentId;
    
    @ManyToOne
    @JoinColumn(name = "programme_id", nullable = false)
    private Programme programme;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Level level;
    
    @Column(nullable = false)
    private Integer yearOfAdmission;
    
    @Column(nullable = false)
    private boolean active = true;
}
