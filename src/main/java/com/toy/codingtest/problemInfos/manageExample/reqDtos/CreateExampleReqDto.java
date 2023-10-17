package com.toy.codingtest.problemInfos.manageExample.reqDtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateExampleReqDto {
    private final String inputValue;
    private final String outputValue;
    private final Long problemId;  
}