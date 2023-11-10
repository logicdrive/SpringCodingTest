package com.toy.codingtest.user.signUp.resDtos;

import com.toy.codingtest.user.components.entities.UserEntity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SignUpResDto {
    private final String email;
    private final String name;

    public SignUpResDto(UserEntity user) {
        this.email = user.getEmail();
        this.name = user.getName();
    }
}
