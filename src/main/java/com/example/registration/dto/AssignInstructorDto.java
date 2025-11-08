package com.example.registration.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignInstructorDto {
    
    @NotNull(message = "Instructor ID is required")
    private Long instructorId;
}
