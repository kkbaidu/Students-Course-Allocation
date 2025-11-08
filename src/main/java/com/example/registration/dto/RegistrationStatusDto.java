package com.example.registration.dto;

import com.example.registration.domain.Semester;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationStatusDto {
    
    @NotNull(message = "Semester is required")
    private Semester semester;
    
    @NotNull(message = "Academic year is required")
    private Integer academicYear;
}
