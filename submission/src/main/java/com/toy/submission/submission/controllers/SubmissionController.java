package com.toy.submission.submission.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toy.submission.submission.executeSubmission.reqDtos.ExecuteSubmissionReqDto;
import com.toy.submission.submission.executeSubmission.resDtos.ExecuteSubmissionResDto;
import com.toy.submission.submission.executeSubmission.services.ExecuteSubmissionService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/submissions")
public class SubmissionController {
    private final ExecuteSubmissionService executeSubmissionService;

    @PostMapping("/execute")
    public ResponseEntity<ExecuteSubmissionResDto> execute(@RequestBody ExecuteSubmissionReqDto executeSubmissionReqDto) {
        this.executeSubmissionService.execute(executeSubmissionReqDto);
        return ResponseEntity.ok(new ExecuteSubmissionResDto(executeSubmissionReqDto));
    }
}
