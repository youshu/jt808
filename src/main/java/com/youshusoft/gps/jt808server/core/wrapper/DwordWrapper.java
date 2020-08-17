package com.youshusoft.gps.jt808server.core.wrapper;

import io.github.hylexus.oaks.utils.IntBitOps;
import io.github.hylexus.oaks.utils.Numbers;

/**
 * 无符号四字节整型(双字，32 位)
 * @author: 悠树
 * @create: 2020-06-20 22:36
 **/
public class DwordWrapper extends ValueWrapper {
    private int readBitIndex;
    public DwordWrapper(byte[] bytes) {
        super(bytes);
        readBitIndex = 0 ;
    }
    public int toInt(){
        return IntBitOps.intFromBytes(bytes);
    }
    public static DwordWrapper valueOf(Integer integer){
        return new DwordWrapper(IntBitOps.intTo4Bytes(integer));
    }
    public boolean getBooleanForBit(int index){
        return Numbers.getBitAt(bytes[0],index)==1?true:false;
    }
    public boolean readBooleanForBit(){
        boolean bool = getBooleanForBit(readBitIndex);
        readBitIndex++;
        return bool;
        
    }
}
