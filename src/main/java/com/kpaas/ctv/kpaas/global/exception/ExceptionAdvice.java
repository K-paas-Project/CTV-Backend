package com.kpaas.ctv.kpaas.global.exception;

import com.kpaas.ctv.kpaas.global.common.dto.BaseResponse;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.Objects;

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
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<BaseResponse> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.of(HttpStatus.METHOD_NOT_ALLOWED, "요청 메서드 잘못됨 " + Arrays.toString(Objects.requireNonNull(ex.getSupportedMethods())) + " 메서드로 요청 부탁");


        return new ResponseEntity<>(baseResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }
}
