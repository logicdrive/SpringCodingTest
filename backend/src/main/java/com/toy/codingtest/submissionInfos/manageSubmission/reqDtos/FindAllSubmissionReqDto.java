package com.toy.codingtest.submissionInfos.manageSubmission.reqDtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FindAllSubmissionReqDto {
    private final int pageNumber;
    private final int pageSize;
}
