package com.toy.submission.submission.executeSubmission.services;

import org.springframework.stereotype.Service;

import com.toy.submission.submission.executeSubmission.reqDtos.ExecuteSubmissionReqDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExecuteSubmissionService {
    public void execute(ExecuteSubmissionReqDto executeSubmissionReqDto) {
        System.out.println("ExecuteSubmissionService.executed() Logics...");
    }
}
