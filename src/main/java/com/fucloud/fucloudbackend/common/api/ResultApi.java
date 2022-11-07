package com.fucloud.fucloudbackend.common.api;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Optional;

@Data
@NoArgsConstructor
public class ResultApi<T> implements Serializable {

    private static final long serialVersionUID = -4153430394359594346L;
    /**
     * 业务状态码
     */
    private long code;
    /**
     * 结果集
     */
    private T data;
    /**
     * 接口描述
     */
    private String message;

    /**
     * 全参
     *
     * @param code    业务状态码
     * @param message 描述
     * @param data    结果集
     */
    public ResultApi(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultApi(ErrorCode errorCode) {
        errorCode = Optional.ofNullable(errorCode).orElse(ErrorCode.FAILED);
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    /**
     * 成功
     *
     * @param
     * @return {code:200,message:操作成功,data:自定义}
     */
    public static <T> ResultApi<T> success() {
        return new ResultApi<T>(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage(), null);
    }

    /**
     * 成功
     *
     * @param data 结果集
     * @return {code:200,message:操作成功,data:自定义}
     */
    public static <T> ResultApi<T> success(T data) {
        return new ResultApi<T>(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功
     *
     * @param data    结果集
     * @param message 自定义提示信息
     * @return {code:200,message:自定义,data:自定义}
     */
    public static <T> ResultApi<T> success(T data, String message) {
        return new ResultApi<T>(ErrorCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     */
    public static <T> ResultApi<T> failed() {
        return failed(ErrorCode.FAILED);
    }

    /**
     * 失败返回结果
     *
     * @param message 提示信息
     * @return {code:枚举ErrorCode取,message:自定义,data:null}
     */
    public static <T> ResultApi<T> failed(String message) {
        return new ResultApi<T>(ErrorCode.FAILED.getCode(), message, null);
    }

    /**
     * 失败
     *
     * @param errorCode 错误码
     * @return {code:封装接口取,message:封装接口取,data:null}
     */
    public static <T> ResultApi<T> failed(MyErrorCode errorCode) {
        return new ResultApi<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     * @param message   错误信息
     * @return {code:枚举ErrorCode取,message:自定义,data:null}
     */
    public static <T> ResultApi<T> failed(MyErrorCode errorCode, String message) {
        return new ResultApi<T>(errorCode.getCode(), message, null);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> ResultApi<T> validateFailed() {
        return failed(ErrorCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> ResultApi<T> validateFailed(String message) {
        return new ResultApi<T>(ErrorCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> ResultApi<T> unauthorized(T data) {
        return new ResultApi<T>(ErrorCode.UNAUTHORIZED.getCode(), ErrorCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> ResultApi<T> forbidden(T data) {
        return new ResultApi<T>(ErrorCode.FORBIDDEN.getCode(), ErrorCode.FORBIDDEN.getMessage(), data);
    }

}
