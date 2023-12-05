package com.kpaas.ctv.kpaas.domain.auth.exception;

import com.kpaas.ctv.kpaas.domain.auth.exception.error.AuthError;
import com.kpaas.ctv.kpaas.global.exception.BusinessException;
import com.kpaas.ctv.kpaas.global.exception.error.ErrorProperty;

public class AuthErrorFailTokenException extends BusinessException {

    public static final AuthErrorFailTokenException EXCEPTION = new AuthErrorFailTokenException();
    public AuthErrorFailTokenException() {
        super(AuthError.AUTH_ERROR_FAIL_TOKEN);
    }
}
