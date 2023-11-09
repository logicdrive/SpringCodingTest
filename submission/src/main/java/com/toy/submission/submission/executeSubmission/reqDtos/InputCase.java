package com.toy.submission.submission.executeSubmission.reqDtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class InputCase {
    private Long testCaseId;
    private String input;
}
