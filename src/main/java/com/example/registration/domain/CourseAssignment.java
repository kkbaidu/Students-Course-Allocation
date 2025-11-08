package com.example.registration.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "course_assignments", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"course_id", "instructor_id"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseAssignment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
    
    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private User instructor;
    
    @Column(nullable = false)
    private LocalDateTime assignedDate;
    
    @Column(nullable = false)
    private boolean active = true;
    
    @PrePersist
    protected void onCreate() {
        assignedDate = LocalDateTime.now();
    }
}
