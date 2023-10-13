package com.frame.common.exception;

import com.frame.common.base.Result;
import com.frame.common.base.ResultCode;
import com.frame.common.base.ResultMessage;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author chenyuntao
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthErrorException.class)
    public Result handleAuthErrorException(AuthErrorException e) {
        e.printStackTrace();
        return Result.fail(ResultCode.UNAUTHORIZED, e.getMessage());
    }

    @ExceptionHandler(BizErrorException.class)
    public Result handleBizErrorException(BizErrorException e) {
        e.printStackTrace();
        return Result.fail(ResultCode.DEFAULT_ERROR, e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public Result handleRuntimeException(RuntimeException e) {
        e.printStackTrace();
        return Result.fail(ResultCode.DEFAULT_ERROR, ResultMessage.DEFAULT_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        e.printStackTrace();
        return Result.fail(ResultCode.DEFAULT_ERROR, ResultMessage.DEFAULT_ERROR);
    }
}
