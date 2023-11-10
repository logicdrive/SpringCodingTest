package com.toy.codingtest.problemInfos.manageProblem.reqDtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FindAllProblemReqDto {
    private int pageNumber;
    private int pageSize;
}
