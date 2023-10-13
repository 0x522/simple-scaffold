package com.frame.common.base;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenyuntao
 */
@Data
public class Result<T> implements Serializable {

    private Boolean success;

    private Integer code;

    private String message;

    private T data;

    public static Result<?> ok() {
        Result<Object> result = new Result<>();
        result.setCode(ResultCode.DEFAULT_SUCCESS);
        result.setMessage(ResultMessage.DEFAULT_SUCCESS);
        result.setSuccess(true);
        return result;
    }

    public static Result<?> ok(Integer resultCode, String resultMessage) {
        Result<Object> result = new Result<>();
        result.setCode(resultCode);
        result.setMessage(resultMessage);
        result.setSuccess(true);
        return result;
    }

    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.DEFAULT_SUCCESS);
        result.setMessage(ResultMessage.DEFAULT_SUCCESS);
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> ok(T data, Integer resultCode, String resultMessage) {
        Result<T> result = new Result<>();
        result.setCode(resultCode);
        result.setMessage(resultMessage);
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static Result<?> fail(Integer resultCode, String resultMessage) {
        Result<Object> result = new Result<>();
        result.setCode(resultCode);
        result.setMessage(resultMessage);
        result.setSuccess(false);
        return result;
    }

}
