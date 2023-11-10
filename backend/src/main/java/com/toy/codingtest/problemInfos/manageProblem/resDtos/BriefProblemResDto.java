package com.toy.codingtest.problemInfos.manageProblem.resDtos;

import com.toy.codingtest.problemInfos.components.entities.ProblemEntity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BriefProblemResDto {
    private final Long id;
    private final String title;

    public BriefProblemResDto(ProblemEntity problem) {
        this.id = problem.getId();
        this.title = problem.getTitle();
    }
}
