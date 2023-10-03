package com.toy.codingtest.user.signIn.services;

import org.springframework.stereotype.Service;

import com.toy.codingtest.user.components.entities.UserEntity;
import com.toy.codingtest.user.components.repositories.UserRepository;
import com.toy.codingtest.user.signIn.dtos.SignInDto;
import com.toy.codingtest.user.signIn.exceptions.UserNotFoundException;

@Service
public class SignInService {
    private final UserRepository userRepository;
    
    public SignInService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void signIn(SignInDto signInDto) {
        UserEntity signInUser = this.userRepository.findByEmailAndPassword(signInDto.getEmail(), signInDto.getPassword());
        if(signInUser == null) throw new UserNotFoundException();
    }
}
