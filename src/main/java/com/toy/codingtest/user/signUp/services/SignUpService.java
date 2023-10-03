package com.toy.codingtest.user.signUp.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.toy.codingtest.user.components.entities.UserEntity;
import com.toy.codingtest.user.components.repositories.UserRepository;
import com.toy.codingtest.user.signUp.dtos.SignUpDto;

@Service
public class SignUpService {
    private final UserRepository userRepository;

    public SignUpService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void signUp(SignUpDto signInDto) {
        UserEntity userEntityToSave = new UserEntity();
        userEntityToSave.setId(UUID.randomUUID().toString());
        userEntityToSave.setEmail(signInDto.getEmail());
        userEntityToSave.setPassword(signInDto.getPassword());
        userEntityToSave.setName(signInDto.getName());

        this.userRepository.save(userEntityToSave);
    }
}
