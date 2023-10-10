package com.toy.codingtest.problem.manageProblem.responses;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FindAllProblemResponse {
    private List<BriefProblemResponse> problems;
}
