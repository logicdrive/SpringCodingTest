package com.toy.codingtest.problemInfos.manageProblem.reqDtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateProblemReqDto {
    private final String title;
    private final int timeLimitSecond;
    private final int memoryLimitMb;

    private final String problemExplain;
    private final String inputExplain;
    private final String outputExplain;
    private final String note;   
}