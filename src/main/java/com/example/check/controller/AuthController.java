package com.example.check.controller;

import com.example.check.payload.request.AuthRequest;
import com.example.check.payload.response.TokenResponse;
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
    @ResponseBody
    public TokenResponse signIn(@RequestBody AuthRequest authRequest) {
        return authService.signIn(authRequest);
    }

}
