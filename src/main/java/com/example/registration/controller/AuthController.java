package com.example.registration.controller;

import com.example.registration.dto.AuthRequest;
import com.example.registration.dto.AuthResponse;
import com.example.registration.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication endpoints for login and token generation")
public class AuthController {
    
    private final AuthService authService;
    
    @PostMapping("/login")
    @Operation(
        summary = "User login",
        description = "Authenticate user with username and password. Returns JWT token for subsequent API calls. " +
                     "Use credentials: admin/admin123, student1/student123, or dr.mensah/instructor123"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Login successful",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = AuthResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Invalid credentials",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid request format",
            content = @Content
        )
    })
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
