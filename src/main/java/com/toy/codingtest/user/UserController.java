package com.toy.codingtest.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toy.codingtest.user.signUp.services.SignUpService;
import com.toy.codingtest.user.signUp.dtos.SignUpDto;
import com.toy.codingtest.user.signIn.services.SignInService;
import com.toy.codingtest.user.signIn.dtos.SignInDto;

@RestController
@RequestMapping("/users")
public class UserController {
    private final SignUpService signUpService;
    private final SignInService signInService;

    public UserController(SignUpService signUpService, SignInService signInService) {
        this.signUpService = signUpService;
        this.signInService = signInService;
    }


    @PostMapping
    public void signUp(@RequestBody SignUpDto signUpDto) {
        this.signUpService.signUp(signUpDto);
    }

    @GetMapping
    public void signIn(@RequestBody SignInDto signInDto) {
        this.signInService.signIn(signInDto);
    }
}
