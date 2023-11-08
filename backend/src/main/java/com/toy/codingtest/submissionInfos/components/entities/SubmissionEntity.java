package com.toy.codingtest.submissionInfos.components.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.toy.codingtest.problemInfos.components.entities.ProblemEntity;
import com.toy.codingtest.user.components.entities.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "submission")
public class SubmissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(nullable = true)
    private int timeMilisecond;

    @Column(nullable = true)
    private int memoryKb;

    @Column(nullable = false)
    private String verdict;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false)
    @Lob
    private String code;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false, updatable = false)
    private Date sentAt;

    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
    private Date judgedAt;


    @ManyToOne
    @JoinColumn(name = "problemId", nullable = false)
    private ProblemEntity problem;

    @ManyToOne
    @JoinColumn(name = "creatorId", nullable = false)
    private UserEntity creator;


    @PrePersist
    void onPrePersist() {
      this.sentAt = new java.util.Date();
    }
}
