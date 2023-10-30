package com.toy.codingtest.submissionInfos.manageTestcase.resDtos;

import com.toy.codingtest.submissionInfos.components.entities.TestcaseEntity;

import lombok.Getter;

@Getter
public class CreateTestcaseResDto {
    private final Long id;
    private final String inputValue;
    private final String outputValue;
    private final Long problemId;
    private final int priority;

    public CreateTestcaseResDto(TestcaseEntity testcase) {
        this.id = testcase.getId();
        this.inputValue = testcase.getInputValue();
        this.outputValue = testcase.getOutputValue();
        this.problemId = testcase.getProblem().getId();
        this.priority = testcase.getPriority();
    }
}