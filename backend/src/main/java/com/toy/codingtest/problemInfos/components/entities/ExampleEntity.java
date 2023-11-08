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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "example")
public class ExampleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = true)
    @Lob
    private String inputValue;

    @Column(nullable = false)
    @Lob
    private String outputValue;

    @Column(nullable = false)
    private int priority;


    @ManyToOne
    @JoinColumn(name = "problemId", nullable = false)
    private ProblemEntity problem;    
}
