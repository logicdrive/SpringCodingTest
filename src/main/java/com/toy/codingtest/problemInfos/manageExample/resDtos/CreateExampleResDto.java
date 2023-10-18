package com.toy.codingtest.problemInfos.manageExample.resDtos;

import com.toy.codingtest.problemInfos.components.entities.ExampleEntity;

import lombok.Getter;

@Getter
public class CreateExampleResDto {
    private final Long id;
    private final String inputValue;
    private final String outputValue;
    private final Long problemId;
    private final int priority;

    public CreateExampleResDto(ExampleEntity example) {
        this.id = example.getId();
        this.inputValue = example.getInputValue();
        this.outputValue = example.getOutputValue();
        this.problemId = example.getProblem().getId();
        this.priority = example.getPriority();
    }
}