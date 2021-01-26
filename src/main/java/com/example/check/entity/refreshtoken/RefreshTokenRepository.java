package com.example.check.entity.refreshtoken;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository {
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
