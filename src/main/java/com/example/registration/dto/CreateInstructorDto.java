package com.example.registration.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Request to create a new instructor account")
public class CreateInstructorDto {
    
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Schema(description = "Unique username for the instructor", example = "dr.osei")
    private String username;
    
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @Schema(description = "Password for the instructor account", example = "instructor123")
    private String password;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Schema(description = "Email address of the instructor", example = "dr.osei@university.edu.gh")
    private String email;
    
    @NotBlank(message = "Full name is required")
    @Schema(description = "Full name of the instructor", example = "Dr. Kwame Osei")
    private String fullName;
    
    @Schema(description = "Department of the instructor", example = "Computer Science")
    private String department;
    
    @Schema(description = "Title/Position of the instructor", example = "Senior Lecturer")
    private String title;
}
