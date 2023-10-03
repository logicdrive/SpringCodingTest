package com.toy.codingtest.components.customExceptionControl;

import org.springframework.http.ResponseEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomExceptionResponseEntity {
    private int status;
    private String code;
    private String message;

    public static ResponseEntity<CustomExceptionResponseEntity> toResponseEntity(CustomException e) {
        return ResponseEntity
            .status(e.getHttpStatus())
            .body(CustomExceptionResponseEntity.builder()
                    .status(e.getHttpStatus().value())
                    .code(e.getCode())
                    .message(e.getMessage())
                    .build()
            );
    }
}
