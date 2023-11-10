package com.toy.codingtest.submissionInfos.manageSubmission.reqDtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CreateSubmissionReqDto {
    private Long problemId;
    private String language;
    private String code;
}
