package com.toy.codingtest.user.components.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toy.codingtest.user.components.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByEmailAndPassword(String email, String password);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByName(String name);
}
