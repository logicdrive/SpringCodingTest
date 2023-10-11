package com.toy.codingtest.problem;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toy.codingtest.problem.manageProblem.reqDtos.CreateProblemReqDto;
import com.toy.codingtest.problem.manageProblem.reqDtos.FindAllProblemReqDto;
import com.toy.codingtest.problem.manageProblem.resDtos.CreateProblemResDto;
import com.toy.codingtest.problem.manageProblem.resDtos.FindAllProblemResDto;
import com.toy.codingtest.problem.manageProblem.services.ManageProblemService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/problems")
public class ProblemController {
    private final ManageProblemService manageProblemService;

    @PostMapping
    public ResponseEntity<CreateProblemResDto> create(@RequestBody CreateProblemReqDto createProblemReqDto) {
        return ResponseEntity.ok(
            new CreateProblemResDto(this.manageProblemService.create(createProblemReqDto))
        );
    }

    @GetMapping
    public ResponseEntity<FindAllProblemResDto> findAll(@ModelAttribute FindAllProblemReqDto findAllProblemReqDto) {
        return ResponseEntity.ok(
            new FindAllProblemResDto(this.manageProblemService.findAll(findAllProblemReqDto))
        );
    }
}
