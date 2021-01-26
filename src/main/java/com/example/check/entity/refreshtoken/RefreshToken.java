package com.example.check.entity.refreshtoken;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Indexed;

import javax.persistence.Id;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {

    @Id
    private String id;

    private String refreshToken;

    private Long refreshExp;

    public RefreshToken update(Long refreshExp) {
        this.refreshExp = refreshExp;
        return this;
    }

}
