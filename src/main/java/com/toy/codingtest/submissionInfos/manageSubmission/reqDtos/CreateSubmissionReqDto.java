package com.toy.codingtest.submissionInfos.manageSubmission.reqDtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateSubmissionReqDto {
    private final Long problemId;
    private final String language;
    private final String code;  
}