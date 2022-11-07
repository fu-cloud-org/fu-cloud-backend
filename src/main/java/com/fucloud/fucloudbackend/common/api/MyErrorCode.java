package com.fucloud.fucloudbackend.common.api;

public interface MyErrorCode {

    /**
     * code: -1:failed, 200:success
     *
     * @return error code
     */
    Integer getCode();

    /**
     * error message
     *
     * @return error message
     */
    String getMessage();

}
