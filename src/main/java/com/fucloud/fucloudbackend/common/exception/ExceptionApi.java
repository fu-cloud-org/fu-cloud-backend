package com.fucloud.fucloudbackend.common.exception;

import com.fucloud.fucloudbackend.common.api.ErrorCode;

public class ExceptionApi extends RuntimeException {

    private ErrorCode errorCode;

    public ExceptionApi(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ExceptionApi(String message) {
        super(message);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
