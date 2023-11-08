package com.toy.codingtest.problemInfos.components.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.toy.codingtest.user.components.entities.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "problem")
public class ProblemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int timeLimitSecond;

    @Column(nullable = false)
    private int memoryLimitMb;
    

    @Column(nullable = false)
    @Lob
    private String problemExplain;

    @Column(nullable = false)
    @Lob
    private String inputExplain;

    @Column(nullable = false)
    @Lob
    private String outputExplain;

    @Lob
    private String note;


    @ManyToOne
    @JoinColumn(name = "creatorId", nullable = false)
    private UserEntity creator;
}
