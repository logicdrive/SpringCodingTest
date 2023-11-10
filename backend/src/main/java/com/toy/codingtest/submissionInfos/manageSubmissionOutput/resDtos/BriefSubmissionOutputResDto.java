package com.toy.codingtest.submissionInfos.manageSubmissionOutput.resDtos;

import com.toy.codingtest.submissionInfos.components.entities.SubmissionOutputEntity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BriefSubmissionOutputResDto {
    private final Long id;
    private final int timeMilisecond;
    private final int memoryKb;
    private final String verdict;
    private final Long testcaseId;
    private final Long submissionId;

    public BriefSubmissionOutputResDto(SubmissionOutputEntity submissionOutputEntity) {
        this.id = submissionOutputEntity.getId();
        this.timeMilisecond = submissionOutputEntity.getTimeMilisecond();
        this.memoryKb = submissionOutputEntity.getMemoryKb();
        this.verdict = submissionOutputEntity.getVerdict();
        this.testcaseId = submissionOutputEntity.getTestcase().getId();
        this.submissionId = submissionOutputEntity.getSubmission().getId();
    }
}