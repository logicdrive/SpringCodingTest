package com.toy.codingtest.user.signIn.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.toy.codingtest.user.components.entities.UserEntity;
import com.toy.codingtest.user.components.repositories.UserRepository;
import com.toy.codingtest.user.signIn.dtos.SignInDto;

@Service
public class SignInService {
    private final UserRepository userRepository;

    public SignInService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void signIn(SignInDto signInDto) {
        UserEntity userEntityToSave = new UserEntity();
        userEntityToSave.setId(UUID.randomUUID().toString());
        userEntityToSave.setEmail(signInDto.getEmail());
        userEntityToSave.setPassword(signInDto.getPassword());
        userEntityToSave.setName(signInDto.getName());

        this.userRepository.save(userEntityToSave);
    }
}
