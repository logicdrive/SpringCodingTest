package com.toy.submission.submission.executeSubmission.reqDtos;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ExecuteSubmissionReqDto {
    private Long submissionId;
    private String code;
    private String language;
    private int timeLimitSecond;
    private int memoryLimitMb;
    private List<InputCaseDto> inputCases;
}