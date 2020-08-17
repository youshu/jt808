package com.youshusoft.gps.jt808server.core.wrapper;

import com.youshusoft.gps.jt808server.core.exception.ValueWrapperEncodingException;

import java.io.UnsupportedEncodingException;

/**
 * GBK 编码，若无数据，置空
 * @author: 悠树
 * @create: 2020-06-20 22:37
 **/
public class StringWrapper extends ValueWrapper {
    public StringWrapper(byte[] bytes) {
        super(bytes);
    }
    public static StringWrapper valueOf(String str){
        try {
            return new StringWrapper(str.getBytes(CHARSET_NAME));
        } catch (UnsupportedEncodingException e) {
            throw new ValueWrapperEncodingException(e);
        }
    }

    @Override
    public String toString() {
        try {
            return new String(bytes,CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            throw new ValueWrapperEncodingException(e);
        }
    }
}
