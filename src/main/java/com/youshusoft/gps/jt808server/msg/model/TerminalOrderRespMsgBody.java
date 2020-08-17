package com.youshusoft.gps.jt808server.msg.model;

import com.youshusoft.gps.jt808server.core.wrapper.ByteWrapper;

/**
 * @author: 悠树
 * @create: 2020-07-08 20:07
 **/
public class TerminalOrderRespMsgBody implements RespMsgBody {
    public static final ByteWrapper RESTART = ByteWrapper.valueOf(0x04);
    private ByteWrapper b;
    public TerminalOrderRespMsgBody(byte b) {
        this(ByteWrapper.valueOf(b));
    }
    public TerminalOrderRespMsgBody(ByteWrapper b) {
        this.b = b;
    }
    @Override
    public byte[] toBytes() {
        return b.getBytes();
    }

    @Override
    public Integer replyMsgType() {
        return Jt808MsgType.ORDER.getMsgId();
    }
}
