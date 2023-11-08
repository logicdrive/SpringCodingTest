package com.toy.codingtest.submissionInfos.manageTestcase.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.toy.codingtest.problemInfos.components.entities.ProblemEntity;
import com.toy.codingtest.problemInfos.components.exceptions.ProblemNotFoundException;
import com.toy.codingtest.problemInfos.components.repositories.ProblemRepository;
import com.toy.codingtest.submissionInfos.components.entities.TestcaseEntity;
import com.toy.codingtest.submissionInfos.components.repositories.TestcaseRepository;
import com.toy.codingtest.submissionInfos.manageTestcase.reqDtos.CreateTestcaseReqDto;
import com.toy.codingtest.submissionInfos.manageTestcase.reqDtos.FindAllTestcaseReqDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManageTestcaseService {
    private final TestcaseRepository testcaseRepository;
    private final ProblemRepository problemRepository;

    public TestcaseEntity create(CreateTestcaseReqDto createTestcaseReqDto) {
        ProblemEntity problemForQuery = problemRepository.findById(createTestcaseReqDto.getProblemId())
                                    .orElseThrow(() -> new ProblemNotFoundException());
        
        int priorityForExample = 1;
        List<TestcaseEntity> examplesForPriority = testcaseRepository.findAllByProblemOrderByPriorityAsc(problemForQuery);
        if(examplesForPriority.size() > 0)
            priorityForExample = examplesForPriority.get(examplesForPriority.size()-1).getPriority()+1;

        return this.testcaseRepository.save(
            TestcaseEntity.builder()
                .inputValue(createTestcaseReqDto.getInputValue())
                .outputValue(createTestcaseReqDto.getOutputValue())
                .problem(problemForQuery)
                .priority(priorityForExample)
                .build()
        );
    }

    public List<TestcaseEntity> findAll(FindAllTestcaseReqDto findAllTestcaseReqDto) {
        ProblemEntity problemForQuery = problemRepository.findById(findAllTestcaseReqDto.getProblemId())
                                    .orElseThrow(() -> new ProblemNotFoundException());

        return this.testcaseRepository.findAllByProblemOrderByPriorityAsc(problemForQuery);
    }
}
