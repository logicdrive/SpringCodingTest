package com.toy.codingtest.submissionInfos.manageTestcase.reqDtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateTestcaseReqDto {
    private final String inputValue;
    private final String outputValue;
    private final Long problemId;  
}