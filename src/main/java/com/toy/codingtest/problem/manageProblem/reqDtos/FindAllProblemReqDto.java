package com.toy.codingtest.problem.manageProblem.reqDtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FindAllProblemReqDto {
    private final int pageNumber;
    private final int pageSize;
}
