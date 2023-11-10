package com.toy.codingtest.submissionInfos.manageSubmissionOutput.resDtos;

import com.toy.codingtest.submissionInfos.components.entities.SubmissionOutputEntity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FindOneSubmissionOutputResDto {
    private final Long id;
    private final int timeMilisecond;
    private final int memoryKb;
    private final String verdict;
    private final String inputValue;
    private final String outputValue;
    private final String expectedValue;
    private final int priority;
    private final Long testcaseId;
    private final Long submissionId;

    public FindOneSubmissionOutputResDto(SubmissionOutputEntity submissionOutputEntity) {
        this.id = submissionOutputEntity.getId();
        this.timeMilisecond = submissionOutputEntity.getTimeMilisecond();
        this.memoryKb = submissionOutputEntity.getMemoryKb();
        this.verdict = submissionOutputEntity.getVerdict();
        this.inputValue = submissionOutputEntity.getTestcase().getInputValue();
        this.outputValue = submissionOutputEntity.getOutputValue();
        this.expectedValue = submissionOutputEntity.getTestcase().getOutputValue();
        this.priority = submissionOutputEntity.getPriority();
        this.testcaseId = submissionOutputEntity.getTestcase().getId();
        this.submissionId = submissionOutputEntity.getSubmission().getId();
    }
}