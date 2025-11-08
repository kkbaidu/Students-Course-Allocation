package com.example.registration.dto;

import com.example.registration.domain.Level;
import com.example.registration.domain.Semester;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {
    
    private Long id;
    
    @NotBlank(message = "Course code is required")
    private String courseCode;
    
    @NotBlank(message = "Course name is required")
    private String courseName;
    
    private String description;
    
    @NotNull(message = "Credit hours is required")
    @Min(value = 1, message = "Credit hours must be at least 1")
    private Integer creditHours;
    
    @NotNull(message = "Programme ID is required")
    private Long programmeId;
    
    private String programmeName;
    
    @NotNull(message = "Level is required")
    private Level level;
    
    @NotNull(message = "Semester is required")
    private Semester semester;
    
    private boolean active = true;
}
