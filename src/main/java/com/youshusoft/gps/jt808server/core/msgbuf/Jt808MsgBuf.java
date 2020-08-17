package com.youshusoft.gps.jt808server.core.msgbuf;

import com.youshusoft.gps.jt808server.core.wrapper.*;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @author: 悠树
 * @create: 2020-06-20 22:26
 **/
public class Jt808MsgBuf implements MsgBuf {
    private ByteBuf byteBuf;
    int readerIndex;
    public Jt808MsgBuf(byte[] bytes) {
        this(Unpooled.wrappedBuffer(bytes));
    }
    public Jt808MsgBuf(ByteBuf byteBuf) {
        this.byteBuf = byteBuf;
    }

    @Override
    public int size() {
        return byteBuf.capacity();
    }

    @Override
    public int readableBytes() {
        return byteBuf.readableBytes();
    }

    @Override
    public int readableIndex() {
        return byteBuf.readerIndex();
    }

    @Override
    public byte[] toBytes() {
        byte[] b = new byte[byteBuf.capacity()];
        byteBuf.getBytes(0,b);
        return b;
    }

    @Override
    public WordWrapper readWord() {
        int length = 2;
        byte[] bs = new byte[length];
        byteBuf.readBytes(bs);
//        System.arraycopy(bytes,readerIndex,bs,0,length);
        WordWrapper wordWrapper = new WordWrapper(bs);
        readerIndex += length;
        return wordWrapper;
    }

    @Override
    public DwordWrapper readDword() {
        int length = 4;
        byte[] bs = new byte[length];
        byteBuf.readBytes(bs);
//        System.arraycopy(bytes,readerIndex,bs,0,length);
        DwordWrapper dwordWrapper = new DwordWrapper(bs);
        readerIndex += length;
        return dwordWrapper;
    }

    @Override
    public ByteWrapper readByte() {
        byte b = byteBuf.readByte();
        return new ByteWrapper(b);
    }

    @Override
    public ByteArrayWrapper readByteArray(int length) {
        byte[] bs = new byte[length];
        byteBuf.readBytes(bs);
        ByteArrayWrapper byteArrayWrapper = new ByteArrayWrapper(bs);
        readerIndex += length;
        return byteArrayWrapper;
    }

    @Override
    public MsgBuf readMsgBuf(int length) {
        byte[] bs = new byte[length];
        byteBuf.readBytes(bs);
        return new Jt808MsgBuf(bs);
    }

    @Override
    public BcdWrapper readBcd(int length) {
        byte[] bs = new byte[length];
        byteBuf.readBytes(bs);
        BcdWrapper o = new BcdWrapper(bs);
        return o;
    }

    @Override
    public StringWrapper readString(int length) {
        byte[] bs = new byte[length];
        byteBuf.readBytes(bs);
        StringWrapper o = new StringWrapper(bs);
        readerIndex += length;
        return o;
    }

    @Override
    public WordWrapper getWord(int index) {
        int length = 2;
        byte[] bs = new byte[length];
        byteBuf.getBytes(index,bs);
        WordWrapper o = new WordWrapper(bs);
        return o;
    }

    @Override
    public DwordWrapper getDword(int index) {
        int length = 4;
        byte[] bs = new byte[length];
        byteBuf.getBytes(index,bs);
        DwordWrapper o = new DwordWrapper(bs);
        return o;
    }

    @Override
    public ByteWrapper getByte(int index) {
        byte b = byteBuf.getByte(index);
        return new ByteWrapper(b);
    }

    @Override
    public ByteArrayWrapper getByteArray(int index, int length) {
        byte[] bs = new byte[length];
        byteBuf.getBytes(index,bs);
        ByteArrayWrapper o = new ByteArrayWrapper(bs);
        return o;
    }

    @Override
    public BcdWrapper getBcd(int index, int length) {
        byte[] bs = new byte[length];
        byteBuf.getBytes(index,bs);
        BcdWrapper o = new BcdWrapper(bs);
        return o;
    }

    @Override
    public StringWrapper getString(int index, int length) {
        byte[] bs = new byte[length];
        byteBuf.getBytes(index,bs);
        StringWrapper o = new StringWrapper(bs);
        return o;
    }
    
    
}
