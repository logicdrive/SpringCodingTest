package com.toy.codingtest.submissionInfos.components.exceptions;

import org.springframework.http.HttpStatus;

import com.toy.codingtest.components.customExceptionControl.CustomException;

import lombok.Getter;

@Getter
public class SubmissionOutputNotFoundException extends CustomException {       
    public SubmissionOutputNotFoundException() {
        super(
            HttpStatus.BAD_REQUEST,
            "SubmissionOutputNotFoundException",
            "해당하는 정보에 대한 제출값 관련 내역을 찾을 수 없습니다.\n" +
            "해당 제출값 관련 내역 검색시에 사용한 키워드가 정확한지 확인하세요."
        );
    }
}