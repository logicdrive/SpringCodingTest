package com.toy.codingtest.submissionInfos.manageSubmission.resDtos;

import com.toy.codingtest.submissionInfos.components.entities.SubmissionEntity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateSubmissionResDto {
    private final Long id;
    private final Long problemId;
    private final String createrEmail;

    public CreateSubmissionResDto(SubmissionEntity submission) {
        this.id = submission.getId();
        this.problemId = submission.getProblem().getId();
        this.createrEmail = submission.getCreator().getEmail();
    }
}