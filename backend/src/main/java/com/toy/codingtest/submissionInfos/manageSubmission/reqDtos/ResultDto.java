package com.toy.codingtest.submissionInfos.manageSubmission.reqDtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResultDto {
    private final int timeMilisecond;
    private final int memoryKb;
    private final String output;
    private final Long testcaseId;
}
