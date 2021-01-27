package com.example.check.controller;

import com.example.check.payload.request.AuthRequest;
import com.example.check.payload.response.AuthResponse;
import com.example.check.security.auth.AuthDetails;
import com.example.check.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public String signIn(@RequestBody AuthRequest authRequest) {
        String token = authService.signIn(authRequest);
        System.out.println(token);
        return token;
    }

}
