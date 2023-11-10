package com.toy.codingtest.problemInfos.manageProblem.resDtos;

import com.toy.codingtest.problemInfos.components.entities.ProblemEntity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateProblemResDto {
    private final Long id;
    private final String createrEmail;

    public CreateProblemResDto(ProblemEntity problem) {
        this.id = problem.getId();
        this.createrEmail = problem.getCreator().getEmail();
    }
}