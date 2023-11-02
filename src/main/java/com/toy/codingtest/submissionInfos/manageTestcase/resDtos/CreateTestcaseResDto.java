package com.toy.codingtest.submissionInfos.manageTestcase.resDtos;

import com.toy.codingtest.submissionInfos.components.entities.TestcaseEntity;

import lombok.Getter;

@Getter
public class CreateTestcaseResDto {
    private final Long id;
    private final Long problemId;

    public CreateTestcaseResDto(TestcaseEntity testcase) {
        this.id = testcase.getId();
        this.problemId = testcase.getProblem().getId();
    }
}