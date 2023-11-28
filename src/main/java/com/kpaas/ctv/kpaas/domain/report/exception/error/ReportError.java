package com.kpaas.ctv.kpaas.domain.report.exception.error;

import com.kpaas.ctv.kpaas.global.exception.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReportError implements ErrorProperty {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저를 찾을 수 없습니다."),
    REPORT_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 신고입니다."),
    IMAGE_NOT_IN(HttpStatus.BAD_REQUEST, "이미지를 보내지 않았습니다.");

    private final HttpStatus status;
    private final String message;
}
