package com.example.registration.dto;

import com.example.registration.domain.Semester;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseRegistrationDto {
    
    private Long id;
    private String courseCode;
    private String courseName;
    private Integer creditHours;
    private Semester semester;
    private Integer academicYear;
    private LocalDateTime registrationDate;
}
