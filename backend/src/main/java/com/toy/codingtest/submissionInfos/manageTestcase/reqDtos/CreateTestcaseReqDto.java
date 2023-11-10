package com.toy.codingtest.submissionInfos.manageTestcase.reqDtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CreateTestcaseReqDto {
    private String inputValue;
    private String outputValue;
    private Long problemId;  
}