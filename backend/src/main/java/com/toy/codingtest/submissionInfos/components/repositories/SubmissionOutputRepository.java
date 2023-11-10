package com.toy.codingtest.submissionInfos.components.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toy.codingtest.submissionInfos.components.entities.SubmissionOutputEntity;

public interface SubmissionOutputRepository extends JpaRepository<SubmissionOutputEntity, Long> {
}