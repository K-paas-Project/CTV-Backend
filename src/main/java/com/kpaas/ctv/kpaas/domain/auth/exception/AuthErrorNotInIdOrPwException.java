package com.kpaas.ctv.kpaas.domain.auth.exception;

import com.kpaas.ctv.kpaas.domain.auth.exception.error.AuthError;
import com.kpaas.ctv.kpaas.global.exception.BusinessException;

public class AuthErrorNotInIdOrPwException extends BusinessException {
    public static final AuthErrorNotInIdOrPwException EXCEPTION = new AuthErrorNotInIdOrPwException();

    private AuthErrorNotInIdOrPwException(){
        super(AuthError.AUTH_ERROR_NOT_IN_ID_AND_PASSWORD);
    }
}

