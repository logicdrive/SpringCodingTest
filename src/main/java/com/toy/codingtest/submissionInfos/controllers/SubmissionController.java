package com.toy.codingtest.submissionInfos.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.toy.codingtest.submissionInfos.manageSubmission.reqDtos.CreateSubmissionReqDto;
import com.toy.codingtest.submissionInfos.manageSubmission.reqDtos.FindAllSubmissionReqDto;
import com.toy.codingtest.submissionInfos.manageSubmission.resDtos.CreateSubmissionResDto;
import com.toy.codingtest.submissionInfos.manageSubmission.resDtos.FindAllSubmissionResDto;
import com.toy.codingtest.submissionInfos.manageSubmission.resDtos.FindOneSubmissionResDto;
import com.toy.codingtest.submissionInfos.manageSubmission.services.ManageSubmissionService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/submissionInfos/submissions")
public class SubmissionController {
    private final ManageSubmissionService manageSubmissionService;

    @PostMapping
    public ResponseEntity<CreateSubmissionResDto> create(@RequestBody CreateSubmissionReqDto createSubmissionReqDto) {
        return ResponseEntity.ok(
            new CreateSubmissionResDto(this.manageSubmissionService.create(createSubmissionReqDto))
        );
    }

    @GetMapping
    public ResponseEntity<FindAllSubmissionResDto> findAll(@ModelAttribute FindAllSubmissionReqDto findAllSubmissionReqDto) {
        return ResponseEntity.ok(
            new FindAllSubmissionResDto(this.manageSubmissionService.findAll(findAllSubmissionReqDto))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<FindOneSubmissionResDto> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(
            new FindOneSubmissionResDto(this.manageSubmissionService.findOne(id))
        );
    }
}
