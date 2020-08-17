package com.youshusoft.gps.jt808server.core.msgbuf;

import com.youshusoft.gps.jt808server.core.wrapper.BcdWrapper;
import com.youshusoft.gps.jt808server.core.wrapper.ByteArrayWrapper;
import com.youshusoft.gps.jt808server.core.wrapper.DwordWrapper;
import com.youshusoft.gps.jt808server.core.wrapper.WordWrapper;
import com.youshusoft.gps.jt808server.core.wrapper.StringWrapper;
import com.youshusoft.gps.jt808server.core.wrapper.ByteWrapper;

/**
 * @author: 悠树
 * @create: 2020-06-20 22:52
 **/
public interface MsgBuf {
    //顺序读取方法
    WordWrapper readWord();
    DwordWrapper readDword();
    ByteWrapper readByte();
    ByteArrayWrapper readByteArray(int length);
    MsgBuf readMsgBuf(int length);
    BcdWrapper readBcd(int length);
    StringWrapper readString(int length);
    
    //随机读取方法
    WordWrapper getWord(int index);
    DwordWrapper getDword(int index);
    ByteWrapper getByte(int index);
    ByteArrayWrapper getByteArray(int index, int length);
    BcdWrapper getBcd(int index, int length);
    StringWrapper getString(int index, int length);
//    MsgBuf getMsgBuf(int index,int length);

    /**
     * 大小
     * @return
     */
    int size();

    /**
     * 可读取数
     * @return
     */
    int readableBytes();

    /**
     * 顺序读取索引
     * @return
     */
    int readableIndex();
    byte[] toBytes();
}
