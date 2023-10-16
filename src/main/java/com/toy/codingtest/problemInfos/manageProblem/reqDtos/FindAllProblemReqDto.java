package com.toy.codingtest.problemInfos.manageProblem.reqDtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FindAllProblemReqDto {
    private final int pageNumber;
    private final int pageSize;
}
