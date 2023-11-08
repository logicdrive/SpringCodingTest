package com.toy.codingtest.submissionInfos.manageSubmission.resDtos;

import com.toy.codingtest.submissionInfos.components.entities.SubmissionEntity;

import lombok.Getter;

import java.util.Date;

@Getter
public class FindOneSubmissionResDto {
    private final Long id;
    private final int timeMilisecond;
    private final int memoryKb;
    private final String verdict;
    private final String language;
    private final String code;
    private final Date sentAt;
    private final Date judgedAt;
    private final Long problemId;
    private final String problemTitle;
    private final String creatorEmail;
    private final String creatorName;

    public FindOneSubmissionResDto(SubmissionEntity submission) {
        this.id = submission.getId();
        this.timeMilisecond = submission.getTimeMilisecond();
        this.memoryKb = submission.getMemoryKb();
        this.verdict = submission.getVerdict();
        this.language = submission.getLanguage();
        this.code = submission.getCode();
        this.sentAt = submission.getSentAt();
        this.judgedAt = submission.getJudgedAt();
        this.problemId = submission.getProblem().getId();
        this.problemTitle = submission.getProblem().getTitle();
        this.creatorEmail = submission.getCreator().getEmail();
        this.creatorName = submission.getCreator().getName();
    }
}