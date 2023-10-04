package com.toy.codingtest.user;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.toy.codingtest.user.components.entities.UserEntity;

import com.toy.codingtest.user.signUp.services.SignUpService;
import com.toy.codingtest.user.signUp.dtos.SignUpDto;
import com.toy.codingtest.user.signUp.responses.SignUpResponse;
import com.toy.codingtest.user.signIn.services.SignInService;
import com.toy.codingtest.user.signIn.dtos.SignInDto;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final SignUpService signUpService;
    private final SignInService signInService;

    @PostMapping
    public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpDto signUpDto) {
        UserEntity createdUserEntity = this.signUpService.signUp(signUpDto);
        return ResponseEntity.ok(
            SignUpResponse.builder()
                .email(createdUserEntity.getEmail())
                .name(createdUserEntity.getName())
                .build()
        );
    }

    @GetMapping
    public ResponseEntity<String> signIn(@RequestBody SignInDto signInDto) {
        return ResponseEntity.ok()
          .header(HttpHeaders.AUTHORIZATION, this.signInService.signIn(signInDto))
          .build();
    }
}
