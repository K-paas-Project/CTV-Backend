package com.kpaas.ctv.kpaas.global.exception;

import com.kpaas.ctv.kpaas.global.common.dto.BaseResponse;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @Builder
    public record ErrorResponse(HttpStatus status, String message){}

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<ErrorResponse> handleException(BusinessException ex){
        ErrorResponse response = ErrorResponse.builder()
                .status(ex.getError().getStatus())
                .message(ex.getError().getMessage())
                .build();
        return new ResponseEntity<>(response, ex.getError().getStatus());
    }
}
