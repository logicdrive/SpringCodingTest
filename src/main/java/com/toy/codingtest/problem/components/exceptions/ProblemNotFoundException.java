package com.toy.codingtest.problem.components.exceptions;

import org.springframework.http.HttpStatus;

import com.toy.codingtest.components.customExceptionControl.CustomException;

import lombok.Getter;

@Getter
public class ProblemNotFoundException extends CustomException {       
    public ProblemNotFoundException() {
        super(
            HttpStatus.BAD_REQUEST,
            "ProblemNotFoundException",
            "해당하는 정보에 대한 문제를 찾을 수 없습니다.\n" +
            "해당 문제 검색시에 사용한 키워드가 정확한지 확인하세요."
        );
    }
}