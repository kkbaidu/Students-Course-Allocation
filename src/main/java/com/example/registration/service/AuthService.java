package com.example.registration.service;

import com.example.registration.dto.AuthRequest;
import com.example.registration.dto.AuthResponse;

public interface AuthService {
    
    AuthResponse login(AuthRequest request);
}
