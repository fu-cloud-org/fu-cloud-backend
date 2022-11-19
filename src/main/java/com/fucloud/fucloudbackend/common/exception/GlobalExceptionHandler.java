package com.fucloud.fucloudbackend.common.exception;

import com.fucloud.fucloudbackend.common.api.ResultApi;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = ExceptionApi.class)
    public ResultApi<Map<String, Object>> handle(ExceptionApi e) {
        if (e.getErrorCode() != null) {
            return ResultApi.failed(e.getErrorCode());
        }
        return ResultApi.failed(e.getMessage());
    }

}
