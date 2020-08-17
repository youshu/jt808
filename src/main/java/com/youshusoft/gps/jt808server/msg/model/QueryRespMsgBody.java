package com.youshusoft.gps.jt808server.msg.model;

/**
 * @author: 悠树
 * @create: 2020-07-08 20:07
 **/
public class QueryRespMsgBody implements RespMsgBody {
    @Override
    public byte[] toBytes() {
        return new byte[0];
    }

    @Override
    public Integer replyMsgType() {
        return Jt808MsgType.QUERY.getMsgId();
    }
}
