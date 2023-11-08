package com.toy.codingtest.problemInfos.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toy.codingtest.problemInfos.manageProblem.reqDtos.CreateProblemReqDto;
import com.toy.codingtest.problemInfos.manageProblem.reqDtos.FindAllProblemReqDto;
import com.toy.codingtest.problemInfos.manageProblem.resDtos.CreateProblemResDto;
import com.toy.codingtest.problemInfos.manageProblem.resDtos.FindAllProblemResDto;
import com.toy.codingtest.problemInfos.manageProblem.resDtos.FindOneProblemResDto;
import com.toy.codingtest.problemInfos.manageProblem.services.ManageProblemService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/problemInfos/problems")
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

    @GetMapping("/{id}")
    public ResponseEntity<FindOneProblemResDto> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(
            new FindOneProblemResDto(this.manageProblemService.findOne(id))
        );
    }
}
