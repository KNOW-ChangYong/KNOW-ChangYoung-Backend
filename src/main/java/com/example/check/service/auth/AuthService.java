package com.example.check.service.auth;

import com.example.check.payload.request.AuthRequest;
import com.example.check.payload.response.AuthResponse;
import com.example.check.payload.response.RefreshTokenResponse;

public interface AuthService {
    AuthResponse signIn(AuthRequest authRequest);
}
