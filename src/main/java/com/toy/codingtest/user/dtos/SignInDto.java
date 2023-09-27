package com.toy.codingtest.user.dtos;

import lombok.Data;

@Data
public class SignInDto {
    private String email;
    private String password;
    private String name;
}
