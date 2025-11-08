package com.example.registration.dto;

import com.example.registration.domain.Level;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    
    private Long id;
    private String studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String programmeName;
    private Level level;
    private Integer yearOfAdmission;
    private boolean active;
}
