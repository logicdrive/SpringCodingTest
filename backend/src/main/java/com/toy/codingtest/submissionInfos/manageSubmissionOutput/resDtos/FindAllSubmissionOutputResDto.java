package com.toy.codingtest.submissionInfos.manageSubmissionOutput.resDtos;

import java.util.List;
import java.util.stream.Collectors;

import com.toy.codingtest.submissionInfos.components.entities.SubmissionOutputEntity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FindAllSubmissionOutputResDto {
    private final List<BriefSubmissionOutputResDto> submissionOutputs;

    public FindAllSubmissionOutputResDto(List<SubmissionOutputEntity> submissionOutputs) {
        this.submissionOutputs = submissionOutputs.stream()
            .map(submissionOutput -> new BriefSubmissionOutputResDto(submissionOutput))
            .collect(Collectors.toList());
    }
}
