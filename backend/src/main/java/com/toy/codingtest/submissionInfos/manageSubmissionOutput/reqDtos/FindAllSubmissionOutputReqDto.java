package com.toy.codingtest.submissionInfos.manageSubmissionOutput.reqDtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FindAllSubmissionOutputReqDto {
    private int pageNumber;
    private int pageSize;
    private Long submissionId;
}
