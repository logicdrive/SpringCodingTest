package com.toy.codingtest.submissionInfos.components.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toy.codingtest.problemInfos.components.entities.ProblemEntity;
import com.toy.codingtest.submissionInfos.components.entities.TestcaseEntity;

public interface TestcaseRepository extends JpaRepository<TestcaseEntity, Long> {
    List<TestcaseEntity> findAllByProblemOrderByPriorityAsc(ProblemEntity problem);
}