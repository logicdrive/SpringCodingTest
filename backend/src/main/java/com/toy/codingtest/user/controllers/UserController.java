package com.toy.codingtest.user.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.toy.codingtest.user.signUp.services.SignUpService;
import com.toy.codingtest.user.signUp.reqDtos.SignUpReqDto;
import com.toy.codingtest.user.signUp.resDtos.SignUpResDto;
import com.toy.codingtest.user.signIn.reqDtos.SignInReqDto;
import com.toy.codingtest.user.signIn.services.SignInService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final SignUpService signUpService;
    private final SignInService signInService;

    @PostMapping
    public ResponseEntity<SignUpResDto> signUp(@RequestBody SignUpReqDto signUpReqDto) {
        return ResponseEntity.ok(
            new SignUpResDto(this.signUpService.signUp(signUpReqDto))
        );
    }

    @PostMapping("/signIn")
    public ResponseEntity<String> signIn(@RequestBody SignInReqDto signInReqDtoForToken) {
        return ResponseEntity.ok()
          .header(HttpHeaders.AUTHORIZATION, this.signInService.tokenBySignIn(signInReqDtoForToken))
          .build();
    }
}
