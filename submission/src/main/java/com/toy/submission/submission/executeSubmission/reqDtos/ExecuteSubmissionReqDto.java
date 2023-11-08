package com.toy.submission.submission.executeSubmission.reqDtos;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ExecuteSubmissionReqDto {
    private final Long submissionId;
    private final String code;
    private final String language;
    private final int timeLimitSecond;
    private final int memoryKb;
    private final List<String> inputs;
}