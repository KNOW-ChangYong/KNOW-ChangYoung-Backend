package com.example.check.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RefreshTokenResponse {

    @JsonProperty("access_token")
    private final String accessToken;
}
