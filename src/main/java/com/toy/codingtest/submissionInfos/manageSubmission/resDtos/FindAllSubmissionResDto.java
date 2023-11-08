package com.toy.codingtest.submissionInfos.manageSubmission.resDtos;

import java.util.List;
import java.util.stream.Collectors;

import com.toy.codingtest.submissionInfos.components.entities.SubmissionEntity;

import lombok.Getter;

@Getter
public class FindAllSubmissionResDto {
    private final List<BriefSubmissionResDto> submissions;

    public FindAllSubmissionResDto(List<SubmissionEntity> submissions) {
        this.submissions = submissions.stream()
            .map(submission -> new BriefSubmissionResDto(submission))
            .collect(Collectors.toList());
    }
}