package com.toy.codingtest.components.customExceptionControl;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class CustomException extends RuntimeException {
    protected HttpStatus httpStatus;
    protected String code;
    protected String message;
}
