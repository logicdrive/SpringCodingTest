package com.toy.codingtest.submissionInfos.manageSubmission.resDtos;

import com.toy.codingtest.submissionInfos.components.entities.SubmissionEntity;

import lombok.Getter;
import lombok.ToString;

import java.text.SimpleDateFormat;

@Getter
@ToString
public class FindOneSubmissionResDto {
    private final Long id;
    private final int timeMilisecond;
    private final int memoryKb;
    private final String verdict;
    private final String language;
    private final String code;
    private final String sentAt;
    private final String judgedAt;
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

        if(submission.getSentAt() == null) this.sentAt = "";
        else this.sentAt = (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(submission.getSentAt());

        if(submission.getJudgedAt() == null) this.judgedAt = "";
        else this.judgedAt = (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(submission.getJudgedAt());
        
        this.problemId = submission.getProblem().getId();
        this.problemTitle = submission.getProblem().getTitle();
        this.creatorEmail = submission.getCreator().getEmail();
        this.creatorName = submission.getCreator().getName();
    }
}