package com.toy.codingtest.problem.manageProblem.resDtos;

import com.toy.codingtest.problem.components.entities.ProblemEntity;

import lombok.Getter;

@Getter
public class FindOneProblemResDto {
    private final Long id;

    private final String title;
    private final int timeLimitSecond;
    private final int memoryLimitMb;

    private final String problemExplain;
    private final String inputExplain;
    private final String outputExplain;
    private final String note;

    private final String createrEmail;
    private final String createrName;

    public FindOneProblemResDto(ProblemEntity problem) {
        this.id = problem.getId();

        this.title = problem.getTitle();
        this.timeLimitSecond = problem.getTimeLimitSecond();
        this.memoryLimitMb = problem.getMemoryLimitMb();

        this.problemExplain = problem.getProblemExplain();
        this.inputExplain = problem.getInputExplain();
        this.outputExplain = problem.getOutputExplain();
        this.note = problem.getNote();

        this.createrEmail = problem.getCreator().getEmail();
        this.createrName = problem.getCreator().getName();
    }
}