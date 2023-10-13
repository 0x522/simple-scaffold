package com.frame.common.exception;

/**
 * 用户登录异常
 *
 * @author: chenyuntao
 **/
public class AuthErrorException extends RuntimeException {

    public AuthErrorException() {
    }

    public AuthErrorException(String message) {
        super(message);
    }

    public AuthErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthErrorException(Throwable cause) {
        super(cause);
    }

    public AuthErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
