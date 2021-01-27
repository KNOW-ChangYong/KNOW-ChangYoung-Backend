package com.example.check.service.auth;

import com.example.check.payload.request.AuthRequest;

public interface AuthService {
    String signIn(AuthRequest authRequest);
}
