package com.toy.codingtest.problem.manageProblem.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FindAllProblemDto {
    private final int pageNumber;
    private final int pageSize;
}
