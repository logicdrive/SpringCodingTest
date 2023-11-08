package com.toy.codingtest.submissionInfos.components.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toy.codingtest.submissionInfos.components.entities.SubmissionEntity;

public interface SubmissionRepository extends JpaRepository<SubmissionEntity, Long> {
}