package com.youshusoft.gps.jt808server.core.exception;

/**
 * @author: 悠树
 * @create: 2020-07-10 00:43
 **/
public class ValueWrapperEncodingException extends AbstractJtException {
    public ValueWrapperEncodingException() {
    }

    public ValueWrapperEncodingException(String message) {
        super(message);
    }

    public ValueWrapperEncodingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValueWrapperEncodingException(Throwable cause) {
        super(cause);
    }

    public ValueWrapperEncodingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
