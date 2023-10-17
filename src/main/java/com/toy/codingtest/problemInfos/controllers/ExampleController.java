package com.toy.codingtest.problemInfos.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toy.codingtest.problemInfos.manageExample.reqDtos.CreateExampleReqDto;
import com.toy.codingtest.problemInfos.manageExample.reqDtos.FindAllExampleReqDto;
import com.toy.codingtest.problemInfos.manageExample.resDtos.CreateExampleResDto;
import com.toy.codingtest.problemInfos.manageExample.resDtos.FindAllExampleResDto;
import com.toy.codingtest.problemInfos.manageExample.services.ManageExampleService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/problemInfos/examples")
public class ExampleController {
    private final ManageExampleService manageExampleService;

    @PostMapping
    public ResponseEntity<CreateExampleResDto> create(@RequestBody CreateExampleReqDto createExampleReqDto) {
        return ResponseEntity.ok(
            new CreateExampleResDto(this.manageExampleService.create(createExampleReqDto))
        );
    }

    @GetMapping
    public ResponseEntity<FindAllExampleResDto> findAll(@ModelAttribute FindAllExampleReqDto findAllExampleReqDto) {
        return ResponseEntity.ok(
            new FindAllExampleResDto(this.manageExampleService.findAll(findAllExampleReqDto))
        );
    }
}
