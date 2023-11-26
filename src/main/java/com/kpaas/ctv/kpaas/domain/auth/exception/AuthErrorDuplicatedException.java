package com.kpaas.ctv.kpaas.domain.auth.exception;

import com.kpaas.ctv.kpaas.domain.auth.exception.error.AuthError;
import com.kpaas.ctv.kpaas.global.exception.BusinessException;

public class AuthErrorDuplicatedException extends BusinessException {
    public static final AuthErrorDuplicatedException EXCEPTION = new AuthErrorDuplicatedException();

    private AuthErrorDuplicatedException(){
        super(AuthError.AUTH_ERROR_DUPLICATED_ID);
    }
}
