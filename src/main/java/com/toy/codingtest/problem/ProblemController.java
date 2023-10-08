package com.toy.codingtest.problem;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toy.codingtest.problem.components.entities.ProblemEntity;
import com.toy.codingtest.problem.manageProblem.dtos.CreateProblemDto;
import com.toy.codingtest.problem.manageProblem.responses.CreateProblemResponse;
import com.toy.codingtest.problem.manageProblem.services.ManageProblemService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/problems")
public class ProblemController {
    private final ManageProblemService manageProblemService;

    @PostMapping
    public ResponseEntity<CreateProblemResponse> create(@RequestBody CreateProblemDto createProblemDto) {
        ProblemEntity createdProblemEntity = this.manageProblemService.create(createProblemDto);
        return ResponseEntity.ok(
            CreateProblemResponse.builder()
                .id(createdProblemEntity.getId())
                .title(createdProblemEntity.getTitle())
                .build()
        );
    }
}
