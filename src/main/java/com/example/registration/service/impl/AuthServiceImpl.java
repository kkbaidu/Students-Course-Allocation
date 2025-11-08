package com.example.registration.service.impl;

import com.example.registration.domain.User;
import com.example.registration.dto.AuthRequest;
import com.example.registration.dto.AuthResponse;
import com.example.registration.repository.UserRepository;
import com.example.registration.security.JwtTokenProvider;
import com.example.registration.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserRepository userRepository;
    
    @Override
    public AuthResponse login(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
            )
        );
        
        User user = (User) authentication.getPrincipal();
        String token = tokenProvider.generateToken(user);
        
        return new AuthResponse(token, user.getUsername(), user.getRole().name());
    }
}
