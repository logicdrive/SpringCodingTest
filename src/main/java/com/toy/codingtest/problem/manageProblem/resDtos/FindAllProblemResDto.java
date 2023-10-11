package com.toy.codingtest.problem.manageProblem.resDtos;

import java.util.List;
import java.util.stream.Collectors;

import com.toy.codingtest.problem.components.entities.ProblemEntity;

import lombok.Getter;

@Getter
public class FindAllProblemResDto {
    private final List<BriefProblemResDto> problems;

    public FindAllProblemResDto(List<ProblemEntity> problems) {
        this.problems = problems.stream()
            .map(problem -> new BriefProblemResDto(problem))
            .collect(Collectors.toList());
    }
}