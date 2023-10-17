package com.toy.codingtest.problemInfos.components.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toy.codingtest.problemInfos.components.entities.ExampleEntity;
import com.toy.codingtest.problemInfos.components.entities.ProblemEntity;

public interface ExampleRepository extends JpaRepository<ExampleEntity, Long> {
    List<ExampleEntity> findAllByProblem(ProblemEntity problem);
}