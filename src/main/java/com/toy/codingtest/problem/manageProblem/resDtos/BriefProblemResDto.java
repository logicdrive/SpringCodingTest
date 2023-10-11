package com.toy.codingtest.problem.manageProblem.resDtos;

import com.toy.codingtest.problem.components.entities.ProblemEntity;

import lombok.Getter;

@Getter
public class BriefProblemResDto {
    private Long id;
    private String title;

    public BriefProblemResDto(ProblemEntity problem) {
        this.id = problem.getId();
        this.title = problem.getTitle();
    }
}
