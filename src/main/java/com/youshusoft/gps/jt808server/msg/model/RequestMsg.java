package com.youshusoft.gps.jt808server.msg.model;

import com.youshusoft.gps.jt808server.core.msgbuf.MsgBuf;

/**
 * @author hylexus
 * Created At 2019-08-20 22:01
 */
public interface RequestMsg {
    RequestMsgHeader getHeader();

    MsgBuf getBody();

    byte getCheckSum();

}
