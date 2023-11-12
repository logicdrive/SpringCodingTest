package com.toy.codingtest.submissionInfos.components.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.toy.codingtest.problemInfos.components.entities.ProblemEntity;
import com.toy.codingtest.submissionInfos.components.entities.SubmissionEntity;

public interface SubmissionRepository extends JpaRepository<SubmissionEntity, Long> {
    List<SubmissionEntity> findAllByProblem(ProblemEntity problem, Pageable paging);
}