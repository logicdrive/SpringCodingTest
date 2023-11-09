package com.toy.submission.submission.executeSubmission.reqDtos;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@RequiredArgsConstructor
public class ExecuteSubmissionReqDto {
    private final Long submissionId;
    private final String code;
    private final String language;
    private final int timeLimitSecond;
    private final int memoryLimitMb;
    private final List<InputCaseDto> inputCases;
}