package com.fucloud.fucloudbackend.common.exception;

import com.fucloud.fucloudbackend.common.api.ErrorCode;

public class AssertsApi {
    /**
     * 抛失败异常
     *
     * @param message 说明
     */
    public static void fail(String message) {
        throw new ExceptionApi(message);
    }

    /**
     * 抛失败异常
     *
     * @param errorCode 状态码
     */
    public static void fail(ErrorCode errorCode) {
        throw new ExceptionApi(errorCode);
    }
}
