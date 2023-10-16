package com.toy.codingtest.problemInfos.manageProblem.resDtos;

import com.toy.codingtest.problemInfos.components.entities.ProblemEntity;

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