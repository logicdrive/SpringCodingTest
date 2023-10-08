package com.toy.codingtest.problem.components.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toy.codingtest.problem.components.entities.ProblemEntity;

public interface ProblemRepository extends JpaRepository<ProblemEntity, Long> {
    
}
