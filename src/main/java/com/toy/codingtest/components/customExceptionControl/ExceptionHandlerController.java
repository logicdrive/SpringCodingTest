package com.toy.codingtest.components.customExceptionControl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<CustomExceptionResponseEntity> handleException(CustomException e) {
        return CustomExceptionResponseEntity.toResponseEntity(e);
    }
}
