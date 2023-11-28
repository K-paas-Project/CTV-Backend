package com.kpaas.ctv.kpaas.domain.report.exception;

import com.kpaas.ctv.kpaas.domain.report.exception.error.ReportError;
import com.kpaas.ctv.kpaas.global.exception.BusinessException;

public class ReportException extends BusinessException {

    private static final ReportException USER_NOT_FOUND = new ReportException(ReportError.USER_NOT_FOUND);

    private static final ReportException REPORT_EXCEPTION = new ReportException(ReportError.REPORT_EXCEPTION);
    private static final ReportException IMAGE_NOT_IN_FILE = new ReportException(ReportError.IMAGE_NOT_IN);

    private static final ReportException REPORT_NOT_FOUND_EXCEPTION = new ReportException(ReportError.REPORT_NOT_FOUND_EXCEPTION);

    private static final ReportException NO_ROLE_EXCEPTION = new ReportException(ReportError.NO_ROLE_EXCEPTION);

    public ReportException(ReportError error) {
        super(error);
    }

    public static ReportException userNotFound() {
        return USER_NOT_FOUND;
    }

    public static ReportException reportError(){
        return REPORT_EXCEPTION;
    }

    public static ReportException notImageInFile(){
        return IMAGE_NOT_IN_FILE;
    }

    public static ReportException noRoleException(){
        return NO_ROLE_EXCEPTION;
    }

    public static ReportException notFoundReport(){
        return REPORT_NOT_FOUND_EXCEPTION;
    }
}
