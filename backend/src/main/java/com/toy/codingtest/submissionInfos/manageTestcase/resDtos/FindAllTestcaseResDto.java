package com.toy.codingtest.submissionInfos.manageTestcase.resDtos;

import java.util.List;
import java.util.stream.Collectors;

import com.toy.codingtest.submissionInfos.components.entities.TestcaseEntity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FindAllTestcaseResDto {
    private final List<TestcaseResDto> testcases;

    public FindAllTestcaseResDto(List<TestcaseEntity> testcases) {
        this.testcases = testcases.stream()
            .map(testcase -> new TestcaseResDto(testcase))
            .collect(Collectors.toList());
    }
}