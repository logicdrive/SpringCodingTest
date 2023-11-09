package com.toy.codingtest.submissionInfos.manageSubmission.reqDtos;

import java.util.List;

import lombok.Data;

@Data
public class VerdictSubmissionReqDto {
    private List<ResultDto> results;
}