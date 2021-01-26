package com.example.check.entity.refreshtoken;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RefreshToken implements Serializable {

    @Id
    private String id;

    private String refreshToken;

    private Long refreshExp;

    public RefreshToken update(Long refreshExp) {
        this.refreshExp = refreshExp;
        return this;
    }

}
