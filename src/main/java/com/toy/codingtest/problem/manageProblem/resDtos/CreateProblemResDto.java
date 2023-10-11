package com.toy.codingtest.problem.manageProblem.resDtos;

import com.toy.codingtest.problem.components.entities.ProblemEntity;

import lombok.Getter;

@Getter
public class CreateProblemResDto {
    private final Long id;
    private final String title;

    public CreateProblemResDto(ProblemEntity problem) {
        this.id = problem.getId();
        this.title = problem.getTitle();
    }
}