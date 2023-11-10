package com.toy.codingtest.problemInfos.manageProblem.reqDtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CreateProblemReqDto {
    private String title;
    private int timeLimitSecond;
    private int memoryLimitMb;

    private String problemExplain;
    private String inputExplain;
    private String outputExplain;
    private String note;   
}
