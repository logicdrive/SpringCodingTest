package com.toy.codingtest.problem.manageProblem.responses;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateProblemResponse {
    private Long id;
    private String title;
}