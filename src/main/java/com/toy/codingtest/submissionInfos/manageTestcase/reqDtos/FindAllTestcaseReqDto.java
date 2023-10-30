package com.toy.codingtest.submissionInfos.manageTestcase.reqDtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FindAllTestcaseReqDto {
    private final Long problemId;  
}