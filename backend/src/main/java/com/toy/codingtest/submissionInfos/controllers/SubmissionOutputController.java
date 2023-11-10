package com.toy.codingtest.submissionInfos.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toy.codingtest.submissionInfos.manageSubmissionOutput.reqDtos.FindAllSubmissionOutputReqDto;
import com.toy.codingtest.submissionInfos.manageSubmissionOutput.resDtos.FindAllSubmissionOutputResDto;
import com.toy.codingtest.submissionInfos.manageSubmissionOutput.resDtos.FindOneSubmissionOutputResDto;
import com.toy.codingtest.submissionInfos.manageSubmissionOutput.services.ManageSubmissionOutputService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/submissionInfos/submissionOutputs")
public class SubmissionOutputController {
    private final ManageSubmissionOutputService manageSubmissionOutputService;

    @GetMapping
    public ResponseEntity<FindAllSubmissionOutputResDto> findAll(@ModelAttribute FindAllSubmissionOutputReqDto findAllSubmissionOutputReqDto) {
        return ResponseEntity.ok(
            new FindAllSubmissionOutputResDto(this.manageSubmissionOutputService.findAll(findAllSubmissionOutputReqDto))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<FindOneSubmissionOutputResDto> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(
            new FindOneSubmissionOutputResDto(this.manageSubmissionOutputService.findOne(id))
        );
    }
}
