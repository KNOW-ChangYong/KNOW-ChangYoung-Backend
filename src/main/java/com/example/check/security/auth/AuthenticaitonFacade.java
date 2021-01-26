package com.example.check.security.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticaitonFacade {

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication(); // 사용자 정보 찾기
    }

    public String getStudentId() {
        return this.getAuthentication().getName();
    }

    public boolean isLogined() {
        return this.getAuthentication() != null;
    }

}
