package com.toy.codingtest.problem;

import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toy.codingtest.problem.components.entities.ProblemEntity;
import com.toy.codingtest.problem.manageProblem.dtos.CreateProblemDto;
import com.toy.codingtest.problem.manageProblem.responses.BriefProblemResponse;
import com.toy.codingtest.problem.manageProblem.responses.CreateProblemResponse;
import com.toy.codingtest.problem.manageProblem.responses.FindAllProblemResponse;
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

    @GetMapping
    public ResponseEntity<FindAllProblemResponse> findAll() {
        return ResponseEntity.ok(
            FindAllProblemResponse.builder()
                .problems(
                    this.manageProblemService.findAll()
                        .stream().map(problem -> BriefProblemResponse.builder()
                            .id(problem.getId())
                            .title(problem.getTitle())
                            .build()
                        ).collect(Collectors.toList())
                ).build()
        );
    }
}
