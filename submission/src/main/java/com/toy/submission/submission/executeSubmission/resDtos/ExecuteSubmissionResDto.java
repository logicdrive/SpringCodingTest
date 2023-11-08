package com.toy.submission.submission.executeSubmission.resDtos;

import com.toy.submission.submission.executeSubmission.reqDtos.ExecuteSubmissionReqDto;

import lombok.Getter;

@Getter
public class ExecuteSubmissionResDto {
    private final Long submissionId;

    public ExecuteSubmissionResDto(ExecuteSubmissionReqDto executeSubmissionReqDto) {
        this.submissionId = executeSubmissionReqDto.getSubmissionId();
    }
}