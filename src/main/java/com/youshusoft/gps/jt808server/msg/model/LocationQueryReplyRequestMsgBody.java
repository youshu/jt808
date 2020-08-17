package com.youshusoft.gps.jt808server.msg.model;

import com.youshusoft.gps.jt808server.core.msgbuf.MsgBuf;
import com.youshusoft.gps.jt808server.core.wrapper.WordWrapper;

/**
 * @author: 悠树
 * @create: 2020-07-11 00:14
 **/
public class LocationQueryReplyRequestMsgBody implements RequestMsgBody {
    private WordWrapper flowId;
    private LocationRequestMsgBody location;
    @Override
    public void read(RequestMsgHeader header, MsgBuf msgBuf) {
        flowId = msgBuf.readWord();
        location = new LocationRequestMsgBody();
        location.read(header,msgBuf.readMsgBuf(msgBuf.readableBytes()));
    }
}
