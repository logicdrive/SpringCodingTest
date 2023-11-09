package com.toy.submission.submission.executeSubmission.reqDtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class InputCaseDto {
    private final Long testCaseId;
    private final String input;
}
