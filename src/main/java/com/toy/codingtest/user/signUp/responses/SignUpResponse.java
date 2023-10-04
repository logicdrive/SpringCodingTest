package com.toy.codingtest.user.signUp.responses;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpResponse {
    private String email;
    private String name;
}
