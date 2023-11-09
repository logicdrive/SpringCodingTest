package com.toy.codingtest.submissionInfos.manageSubmission.reqDtos;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExecuteSubmissionReqDto {
    private final Long submissionId;
    private final String code;
    private final String language;
    private final int timeLimitSecond;
    private final int memoryLimitMb;
    private final List<String> inputs;
}