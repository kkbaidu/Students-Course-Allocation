package com.example.registration.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String courseCode;
    
    @Column(nullable = false)
    private String courseName;
    
    @Column(length = 1000)
    private String description;
    
    @Column(nullable = false)
    private Integer creditHours;
    
    @ManyToOne
    @JoinColumn(name = "programme_id", nullable = false)
    private Programme programme;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Level level;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Semester semester;
    
    @Column(nullable = false)
    private boolean active = true;
}
