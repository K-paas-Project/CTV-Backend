package com.kpaas.ctv.kpaas.domain.auth.exception.error;

import com.kpaas.ctv.kpaas.global.exception.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthError implements ErrorProperty {

    AUTH_ERROR_DUPLICATED_ID(HttpStatus.CONFLICT, "동일한 아이디가 존재합니다."),
    AUTH_ERROR_NOT_FOUND_ID_OR_PASSWORD(HttpStatus.NOT_FOUND, "아이디 혹은 비밀번호가 잘못되었습니다."),
    AUTH_ERROR_NOT_IN_ID_AND_PASSWORD(HttpStatus.BAD_REQUEST, "값이 null입니다."),
    AUTH_ERROR_FAIL_TOKEN(HttpStatus.BAD_REQUEST, "토큰이 잘못되었습니다.");

    private final HttpStatus status;
    private final String message;

}
