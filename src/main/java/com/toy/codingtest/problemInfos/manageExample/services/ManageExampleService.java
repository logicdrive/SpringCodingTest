package com.toy.codingtest.problemInfos.manageExample.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.toy.codingtest.problemInfos.components.entities.ExampleEntity;
import com.toy.codingtest.problemInfos.components.entities.ProblemEntity;
import com.toy.codingtest.problemInfos.components.exceptions.ProblemNotFoundException;
import com.toy.codingtest.problemInfos.components.repositories.ExampleRepository;
import com.toy.codingtest.problemInfos.components.repositories.ProblemRepository;
import com.toy.codingtest.problemInfos.manageExample.reqDtos.CreateExampleReqDto;
import com.toy.codingtest.problemInfos.manageExample.reqDtos.FindAllExampleReqDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManageExampleService {
    private final ExampleRepository exampleRepository;
    private final ProblemRepository problemRepository;

    public ExampleEntity create(CreateExampleReqDto createExampleReqDto) {
        ProblemEntity problemForExample = problemRepository.findById(createExampleReqDto.getProblemId())
                                    .orElseThrow(() -> new ProblemNotFoundException());

        return this.exampleRepository.save(
            ExampleEntity.builder()
                .inputValue(createExampleReqDto.getInputValue())
                .outputValue(createExampleReqDto.getOutputValue())
                .problem(problemForExample)
                .build()
        );
    }

    public List<ExampleEntity> findAll(FindAllExampleReqDto findAllExampleReqDto) {
        ProblemEntity problemForExample = problemRepository.findById(findAllExampleReqDto.getProblemId())
                                    .orElseThrow(() -> new ProblemNotFoundException());

        return this.exampleRepository.findAllByProblem(problemForExample);
    }
}
