package com.example.check.controller;

import com.example.check.entity.student.Student;
import com.example.check.entity.student.StudentRepository;
import com.example.check.payload.request.AuthRequest;
import com.example.check.payload.response.AuthResponse;
import com.example.check.payload.response.RefreshTokenResponse;
import com.example.check.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final StudentRepository studentRepository;

    @PostMapping
    public AuthResponse signIn(@RequestBody AuthRequest authRequest) {
        return authService.signIn(authRequest);
    }

    @PutMapping
    public RefreshTokenResponse tokenResponse(@RequestHeader("X-Refresh-Token") String refreshToken) {
        return authService.tokenRefresh(refreshToken);
    }

    @PostMapping("/signup")
    public void signUp() {
        studentRepository.save(Student.builder().build());
    }
}
