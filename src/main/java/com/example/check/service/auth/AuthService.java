package com.example.check.service.auth;

import com.example.check.payload.request.AuthRequest;
import com.example.check.payload.response.TokenResponse;

public interface AuthService {
    TokenResponse signIn(AuthRequest authRequest);
}
