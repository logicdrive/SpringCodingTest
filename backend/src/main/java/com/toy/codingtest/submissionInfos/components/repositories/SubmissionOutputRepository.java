package com.toy.codingtest.submissionInfos.components.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.toy.codingtest.submissionInfos.components.entities.SubmissionEntity;
import com.toy.codingtest.submissionInfos.components.entities.SubmissionOutputEntity;

public interface SubmissionOutputRepository extends JpaRepository<SubmissionOutputEntity, Long> {
    List<SubmissionOutputEntity> findAllBySubmission(SubmissionEntity submission, Pageable paging);
}