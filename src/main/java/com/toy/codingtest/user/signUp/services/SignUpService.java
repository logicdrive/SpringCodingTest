package com.toy.codingtest.user.signUp.services;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.toy.codingtest.user.components.entities.UserEntity;
import com.toy.codingtest.user.components.repositories.UserRepository;
import com.toy.codingtest.user.signUp.reqDtos.SignUpReqDto;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class SignUpService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // Spring Security에서 사용하는 PasswordEncoder를 활용하기 위해서

    public UserEntity signUp(SignUpReqDto signUpReqDto) {
        return this.userRepository.save(
            UserEntity.builder()
                .id(UUID.randomUUID().toString())
                .email(signUpReqDto.getEmail())
                .password(this.passwordEncoder.encode(signUpReqDto.getPassword()))
                .name(signUpReqDto.getName())
                .build()
        );
    }
}
