package com.youshusoft.gps.jt808server.msg.model;

import java.io.IOException;

/**
 * @author hylexus
 * createdAt 2018/12/29
 **/
public interface RespMsgBody {
    byte SUCCESS = 0;
    byte FAILURE = 1;

    byte[] toBytes() throws IOException;
    default Integer replyMsgType() {
        return Jt808MsgType.SERVER_COMMON_REPLY.getMsgId();
    }
}
