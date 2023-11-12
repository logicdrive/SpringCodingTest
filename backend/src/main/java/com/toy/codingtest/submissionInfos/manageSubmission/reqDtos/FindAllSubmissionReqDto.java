package com.toy.codingtest.submissionInfos.manageSubmission.reqDtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FindAllSubmissionReqDto {
    private int pageNumber;
    private int pageSize;
    private String type = "";
    private String query = "";
}
