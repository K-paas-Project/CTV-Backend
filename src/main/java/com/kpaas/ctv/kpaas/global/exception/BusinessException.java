package com.kpaas.ctv.kpaas.global.exception;

import com.kpaas.ctv.kpaas.global.exception.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BusinessException extends RuntimeException{
    private final ErrorProperty error;
}
