package com.toy.codingtest.user.signIn.reqDtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignInReqDto {
    private final String email;
    private final String password;
}
