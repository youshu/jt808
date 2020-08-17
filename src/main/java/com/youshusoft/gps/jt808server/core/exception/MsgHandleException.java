package com.youshusoft.gps.jt808server.core.exception;

/**
 * @author: 悠树
 * @create: 2020-07-10 00:43
 **/
public class MsgHandleException extends AbstractJtException {
    public MsgHandleException() {
    }

    public MsgHandleException(String message) {
        super(message);
    }

    public MsgHandleException(String message, Throwable cause) {
        super(message, cause);
    }

    public MsgHandleException(Throwable cause) {
        super(cause);
    }

    public MsgHandleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
