package com.toy.codingtest.problem.manageProblem.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.toy.codingtest.components.security.JwtTokenService;
import com.toy.codingtest.problem.components.entities.ProblemEntity;
import com.toy.codingtest.problem.components.repositories.ProblemRepository;
import com.toy.codingtest.problem.manageProblem.dtos.CreateProblemDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManageProblemService {
    private final ProblemRepository problemRepository;
    private final JwtTokenService jwtTokenService;

    public ProblemEntity create(CreateProblemDto createProblemDto) {
        return problemRepository.save(
            ProblemEntity.builder()
                .title(createProblemDto.getTitle())
                .timeLimitSecond(createProblemDto.getTimeLimitSecond())
                .memoryLimitMb(createProblemDto.getMemoryLimitMb())

                .problemExplain(createProblemDto.getProblemExplain())
                .inputExplain(createProblemDto.getInputExplain())
                .outputExplain(createProblemDto.getOutputExplain())
                .note(createProblemDto.getNote())

                .creator(this.jwtTokenService.userEntity())
                .build()
        );
    }

    public List<ProblemEntity> findAll() {
        return this.problemRepository.findAll();
    }
}
