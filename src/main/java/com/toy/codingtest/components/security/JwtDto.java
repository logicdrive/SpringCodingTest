package com.toy.codingtest.components.security;

import lombok.Builder;
import lombok.Getter;

// JWT에 담긴 내용들을 간편하게 접근하기 위한 DTO
@Getter
@Builder
public class JwtDto {
    private String email;
    private String name;
}
