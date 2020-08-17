package com.youshusoft.gps.jt808server.core.wrapper;

import com.youshusoft.gps.jt808server.core.exception.ValueWrapperEncodingException;

import java.io.UnsupportedEncodingException;

/**
 * @author: 悠树
 * @create: 2020-06-20 22:35
 **/
public class ByteArrayWrapper extends ValueWrapper {
    public ByteArrayWrapper(byte[] bytes) {
        
        super(bytes);
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
