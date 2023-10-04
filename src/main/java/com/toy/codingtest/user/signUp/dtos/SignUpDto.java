package com.toy.codingtest.user.signUp.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignUpDto {
    private final String email;
    private final String password;
    private final String name;
}
