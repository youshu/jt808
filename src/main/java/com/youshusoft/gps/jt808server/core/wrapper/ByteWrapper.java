package com.youshusoft.gps.jt808server.core.wrapper;

/**
 * 无符号单字节整型(字节，8 位)
 * @author: 悠树
 * @create: 2020-06-20 22:31
 **/
public class ByteWrapper extends ValueWrapper {
    public ByteWrapper(byte b) {
        super(new byte[]{b});
    }
    
    public static ByteWrapper valueOf(byte b){
        return new ByteWrapper(b);
    }
    public static ByteWrapper valueOf(int i){
        return new ByteWrapper((byte)i);
    }
    public int toInt(){
        return toByte();
    }
    public byte toByte(){
        return bytes[0];
    }
}
