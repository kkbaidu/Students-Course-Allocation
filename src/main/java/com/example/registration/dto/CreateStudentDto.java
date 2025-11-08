package com.example.registration.dto;

import com.example.registration.domain.Level;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentDto {
    
    @NotBlank(message = "Username is required")
    private String username;
    
    @NotBlank(message = "Password is required")
    private String password;
    
    @NotBlank(message = "First name is required")
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    private String lastName;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;
    
    @NotBlank(message = "Student ID is required")
    private String studentId;
    
    @NotNull(message = "Programme ID is required")
    private Long programmeId;
    
    @NotNull(message = "Level is required")
    private Level level;
    
    @NotNull(message = "Year of admission is required")
    private Integer yearOfAdmission;
}
