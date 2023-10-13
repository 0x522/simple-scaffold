package com.frame.common.exception;


/**
 * 业务异常
 *
 * @author chenyuntao
 */
public class BizErrorException extends RuntimeException {
    public BizErrorException() {
    }

    public BizErrorException(String message) {
        super(message);
    }

    public BizErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizErrorException(Throwable cause) {
        super(cause);
    }

    public BizErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
