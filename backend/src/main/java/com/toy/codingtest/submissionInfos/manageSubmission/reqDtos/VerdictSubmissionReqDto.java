package com.toy.codingtest.submissionInfos.manageSubmission.reqDtos;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class VerdictSubmissionReqDto {
    private List<ResultDto> results;
}
