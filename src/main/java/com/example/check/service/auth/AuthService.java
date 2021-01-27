package com.example.check.service.auth;

import com.example.check.payload.request.AuthRequest;
import com.example.check.payload.response.AuthResponse;

public interface AuthService {
    String signIn(AuthRequest authRequest);
}
