package com.toy.codingtest.user.signIn.services;

import org.springframework.stereotype.Service;

import com.toy.codingtest.components.security.JwtTokenService;
import com.toy.codingtest.user.signIn.dtos.SignInDto;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class SignInService {
    private final JwtTokenService jwtTokenService;

    public String signIn(SignInDto signInDto) {
        return this.jwtTokenService.tokenValue(signInDto);
    }
}
