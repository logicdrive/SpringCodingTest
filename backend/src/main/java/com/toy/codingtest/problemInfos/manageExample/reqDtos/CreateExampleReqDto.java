package com.toy.codingtest.problemInfos.manageExample.reqDtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CreateExampleReqDto {
    private String inputValue;
    private String outputValue;
    private Long problemId;  
}
