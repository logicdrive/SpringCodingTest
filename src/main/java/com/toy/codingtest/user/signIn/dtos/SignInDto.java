package com.toy.codingtest.user.signIn.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignInDto {
    private final String email;
    private final String password;
}
