package com.toy.codingtest.problem.manageProblem.resDtos;

import com.toy.codingtest.problem.components.entities.ProblemEntity;

import lombok.Getter;

@Getter
public class BriefProblemResDto {
    private final Long id;
    private final String title;

    public BriefProblemResDto(ProblemEntity problem) {
        this.id = problem.getId();
        this.title = problem.getTitle();
    }
}
