package com.kpaas.ctv.kpaas.domain.auth.exception;

import com.kpaas.ctv.kpaas.domain.auth.exception.error.AuthError;
import com.kpaas.ctv.kpaas.global.exception.BusinessException;

public class AuthErrorNotFoundException extends BusinessException {
    public static final AuthErrorNotFoundException EXCEPTION = new AuthErrorNotFoundException();

    private AuthErrorNotFoundException(){
        super(AuthError.AUTH_ERROR_NOT_FOUND_ID_AND_PASSWORD);
    }
}
