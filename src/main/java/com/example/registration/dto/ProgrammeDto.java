package com.example.registration.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgrammeDto {
    
    private Long id;
    
    @NotBlank(message = "Programme code is required")
    private String code;
    
    @NotBlank(message = "Programme name is required")
    private String name;
    
    private String description;
    
    private boolean active = true;
}
