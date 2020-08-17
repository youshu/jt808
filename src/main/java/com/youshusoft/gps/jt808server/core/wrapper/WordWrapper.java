package com.youshusoft.gps.jt808server.core.wrapper;

import io.github.hylexus.oaks.utils.IntBitOps;
import lombok.EqualsAndHashCode;

/**
 * 无符号双字节整型(字，16 位)
 * @author: 悠树
 * @create: 2020-06-20 22:36
 **/
@EqualsAndHashCode
public class WordWrapper extends ValueWrapper {
    public WordWrapper(byte[] bytes) {
        super(bytes);
    }
    public int toInt(){
        return IntBitOps.intFromBytes(bytes);
    }
    public static WordWrapper valueOf(Integer integer){
        return new WordWrapper(IntBitOps.intTo2Bytes(integer));
    }
}
