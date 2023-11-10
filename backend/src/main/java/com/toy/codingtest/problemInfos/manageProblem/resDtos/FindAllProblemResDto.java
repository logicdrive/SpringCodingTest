package com.toy.codingtest.problemInfos.manageProblem.resDtos;

import java.util.List;
import java.util.stream.Collectors;

import com.toy.codingtest.problemInfos.components.entities.ProblemEntity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FindAllProblemResDto {
    private final List<BriefProblemResDto> problems;

    public FindAllProblemResDto(List<ProblemEntity> problems) {
        this.problems = problems.stream()
            .map(problem -> new BriefProblemResDto(problem))
            .collect(Collectors.toList());
    }
}