package com.youshusoft.gps.jt808server.msg.model;

import com.youshusoft.gps.jt808server.core.msgbuf.MsgBuf;
import com.youshusoft.gps.jt808server.core.wrapper.StringWrapper;
import lombok.Data;

/**
 * @author: 悠树
 * @create: 2020-06-21 17:48
 **/
@Data
public class AuthRequestMsgBody implements RequestMsgBody{
    private StringWrapper authCode;

    @Override
    public void read(RequestMsgHeader header, MsgBuf msgBuf) {
        this.setAuthCode(msgBuf.readString(header.getMsgBodyLength()));
    }
}
