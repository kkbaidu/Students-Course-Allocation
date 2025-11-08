package com.example.registration.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Instructor information response")
public class InstructorDto {
    
    @Schema(description = "Unique identifier of the instructor", example = "1")
    private Long id;
    
    @Schema(description = "Username of the instructor", example = "dr.mensah")
    private String username;
    
    @Schema(description = "Email address of the instructor", example = "dr.mensah@university.edu.gh")
    private String email;
    
    @Schema(description = "Full name of the instructor", example = "Dr. Samuel Mensah")
    private String fullName;
    
    @Schema(description = "Department of the instructor", example = "Computer Science")
    private String department;
    
    @Schema(description = "Title/Position of the instructor", example = "Senior Lecturer")
    private String title;
    
    @Schema(description = "Account status", example = "true")
    private Boolean enabled;
}
