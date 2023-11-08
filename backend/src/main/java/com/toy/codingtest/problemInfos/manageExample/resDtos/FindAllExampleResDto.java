package com.toy.codingtest.problemInfos.manageExample.resDtos;

import java.util.List;
import java.util.stream.Collectors;

import com.toy.codingtest.problemInfos.components.entities.ExampleEntity;

import lombok.Getter;

@Getter
public class FindAllExampleResDto {
    private final List<ExampleResDto> examples;

    public FindAllExampleResDto(List<ExampleEntity> examples) {
        this.examples = examples.stream()
            .map(example -> new ExampleResDto(example))
            .collect(Collectors.toList());
    }
}