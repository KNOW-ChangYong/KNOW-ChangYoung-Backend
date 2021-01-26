package com.example.check.controller.attendance;

import com.example.check.payload.AuthRequest;
import com.example.check.payload.AuthResponse;
import com.example.check.payload.RefreshTokenResponse;
import com.example.check.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Service
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public AuthResponse signIn(@RequestBody AuthRequest authRequest) {
        return authService.signIn(authRequest);
    }

    @PutMapping
    public RefreshTokenResponse tokenResponse(@RequestHeader("X-Refresh-Token") String refreshToken) {
        return authService.tokenRefresh(refreshToken);
    }
}
