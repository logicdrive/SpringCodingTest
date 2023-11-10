package com.toy.codingtest.submissionInfos.manageSubmission.resDtos;

import com.toy.codingtest.submissionInfos.components.entities.SubmissionEntity;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
public class BriefSubmissionResDto {
    private final Long id;
    private final int timeMilisecond;
    private final int memoryKb;
    private final String verdict;
    private final String language;
    private final Date sentAt;
    private final Date judgedAt;
    private final Long problemId;
    private final String problemTitle;
    private final String creatorEmail;
    private final String creatorName;

    public BriefSubmissionResDto(SubmissionEntity submission) {
        this.id = submission.getId();
        this.timeMilisecond = submission.getTimeMilisecond();
        this.memoryKb = submission.getMemoryKb();
        this.verdict = submission.getVerdict();
        this.language = submission.getLanguage();
        this.sentAt = submission.getSentAt();
        this.judgedAt = submission.getJudgedAt();
        this.problemId = submission.getProblem().getId();
        this.problemTitle = submission.getProblem().getTitle();
        this.creatorEmail = submission.getCreator().getEmail();
        this.creatorName = submission.getCreator().getName();
    }
}