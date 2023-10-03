package com.toy.codingtest.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toy.codingtest.user.signUp.dtos.SignUpDto;
import com.toy.codingtest.user.signUp.services.SignUpService;

@RestController
@RequestMapping("/users")
public class UserController {
    private final SignUpService signUpService;

    public UserController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }


    @PostMapping
    public void signUp(@RequestBody SignUpDto signUpDto) {
        this.signUpService.signUp(signUpDto);
    }

}
