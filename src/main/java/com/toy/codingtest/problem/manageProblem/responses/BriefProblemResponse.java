package com.toy.codingtest.problem.manageProblem.responses;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BriefProblemResponse {
    private Long id;
    private String title;
}
