package com.toy.codingtest.submissionInfos.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toy.codingtest.submissionInfos.manageTestcase.reqDtos.CreateTestcaseReqDto;
import com.toy.codingtest.submissionInfos.manageTestcase.reqDtos.FindAllTestcaseReqDto;
import com.toy.codingtest.submissionInfos.manageTestcase.resDtos.CreateTestcaseResDto;
import com.toy.codingtest.submissionInfos.manageTestcase.resDtos.FindAllTestcaseResDto;
import com.toy.codingtest.submissionInfos.manageTestcase.services.ManageTestcaseService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/submissionInfos/testcases")
public class TestcaseController {
    private final ManageTestcaseService manageTestcaseService;

    @PostMapping
    public ResponseEntity<CreateTestcaseResDto> create(@RequestBody CreateTestcaseReqDto createTestcaseReqDto) {
        return ResponseEntity.ok(
            new CreateTestcaseResDto(this.manageTestcaseService.create(createTestcaseReqDto))
        );
    }

    @GetMapping
    public ResponseEntity<FindAllTestcaseResDto> findAll(@ModelAttribute FindAllTestcaseReqDto findAllTestcaseReqDto) {
        return ResponseEntity.ok(
            new FindAllTestcaseResDto(this.manageTestcaseService.findAll(findAllTestcaseReqDto))
        );
    }
}
