package com.example.check.service.auth;

import com.example.check.payload.AuthRequest;
import com.example.check.payload.AuthResponse;
import com.example.check.payload.RefreshTokenResponse;

public interface AuthService {
    AuthResponse signIn(AuthRequest authRequest);

    RefreshTokenResponse tokenRefresh(String token);
}
