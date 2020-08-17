package com.youshusoft.gps.jt808server.core.wrapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author: 悠树
 * @create: 2020-06-20 22:32
 **/
public class ValueWrapper {
    public static final String CHARSET_NAME = "GBK";
    protected byte[] bytes;

    public ValueWrapper(byte[] bytes) {
        this.bytes = bytes;
    }
    public byte[] getBytes(){
        return bytes;
    }
    
    public static byte[] concat(ValueWrapper... valueWrappers){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            for (ValueWrapper valueWrapper : valueWrappers) {
                outputStream.write(valueWrapper.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }
}
