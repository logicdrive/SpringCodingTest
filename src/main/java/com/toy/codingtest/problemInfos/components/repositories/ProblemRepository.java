package com.toy.codingtest.problemInfos.components.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toy.codingtest.problemInfos.components.entities.ProblemEntity;

public interface ProblemRepository extends JpaRepository<ProblemEntity, Long> {
    
}
