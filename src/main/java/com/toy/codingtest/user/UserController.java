package com.toy.codingtest.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toy.codingtest.user.dtos.SignInDto;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public void signIn(@RequestBody SignInDto user) {

    }

}
