package com.toy.codingtest.user.signUp.reqDtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignUpReqDto {
    private final String email;
    private final String password;
    private final String name;
}
