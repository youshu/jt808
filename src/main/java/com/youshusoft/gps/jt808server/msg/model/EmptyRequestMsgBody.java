package com.youshusoft.gps.jt808server.msg.model;

import com.youshusoft.gps.jt808server.core.msgbuf.MsgBuf;
import lombok.Data;

/**
 * @author: 悠树
 * @create: 2020-06-21 17:48
 **/
@Data
public class EmptyRequestMsgBody implements RequestMsgBody{
    @Override
    public void read(RequestMsgHeader header, MsgBuf msgBuf) {
    }
}
