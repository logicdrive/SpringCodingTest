package com.toy.codingtest.problemInfos.manageExample.resDtos;

import com.toy.codingtest.problemInfos.components.entities.ExampleEntity;

import lombok.Getter;

@Getter
public class CreateExampleResDto {
    private final Long id;
    private final Long problemId;

    public CreateExampleResDto(ExampleEntity example) {
        this.id = example.getId();
        this.problemId = example.getProblem().getId();
    }
}