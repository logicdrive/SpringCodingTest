package com.toy.codingtest.problemInfos.components.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.toy.codingtest.problemInfos.components.entities.ProblemEntity;
import com.toy.codingtest.user.components.entities.UserEntity;

public interface ProblemRepository extends JpaRepository<ProblemEntity, Long> {
    List<ProblemEntity> findAllByCreator(UserEntity creator, Pageable paging);
}
