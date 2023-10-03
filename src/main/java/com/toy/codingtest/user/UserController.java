package com.toy.codingtest.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toy.codingtest.user.signIn.dtos.SignInDto;
import com.toy.codingtest.user.signIn.services.SignInService;

@RestController
@RequestMapping("/users")
public class UserController {
    private final SignInService signInService;

    public UserController(SignInService signInService) {
        this.signInService = signInService;
    }


    @PostMapping
    public void signIn(@RequestBody SignInDto user) {
        this.signInService.signIn(user);
    }

}
