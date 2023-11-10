package com.toy.codingtest.submissionInfos.manageSubmission.reqDtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ResultDto {
    private int timeMilisecond;
    private int memoryKb;
    private String output;
    private Long testcaseId;
}
