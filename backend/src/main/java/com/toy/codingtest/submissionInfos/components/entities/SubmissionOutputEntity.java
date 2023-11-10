package com.toy.codingtest.submissionInfos.components.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "submission_output")
public class SubmissionOutputEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(nullable = true)
    private int timeMilisecond;

    @Column(nullable = true)
    private int memoryKb;

    @Column(nullable = false)
    private String verdict;


    @ManyToOne
    @JoinColumn(name = "testcaseId", nullable = false)
    private TestcaseEntity testcase;

    @ManyToOne
    @JoinColumn(name = "submissionId", nullable = false)
    private SubmissionEntity submissionId;
}
