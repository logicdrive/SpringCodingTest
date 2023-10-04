package com.toy.codingtest.user.manageUser.services;

import org.springframework.stereotype.Service;

import com.toy.codingtest.user.components.entities.UserEntity;
import com.toy.codingtest.user.components.exceptions.UserNotFoundException;
import com.toy.codingtest.user.components.repositories.UserRepository;

@Service
public class ManageUserService {
    private final UserRepository userRepository;

    public ManageUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserEntity findByEmail(String email) {
        return this.userRepository.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException());
    }
}
