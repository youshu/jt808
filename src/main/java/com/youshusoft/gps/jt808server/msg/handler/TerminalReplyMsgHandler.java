package com.youshusoft.gps.jt808server.msg.handler;

import com.youshusoft.gps.jt808server.msg.model.Jt808MsgType;
import com.youshusoft.gps.jt808server.msg.model.RequestMsgHeader;
import com.youshusoft.gps.jt808server.msg.model.RespMsgBody;
import com.youshusoft.gps.jt808server.msg.model.TerminalReplyMsgBody;

/**
 * 设备通用回复
 * @author: 悠树
 * @create: 2020-06-21 17:50
 **/
public class TerminalReplyMsgHandler extends AbstractJt808RequestMsgHandler<TerminalReplyMsgBody> {

    @Override
    protected RespMsgBody handle(RequestMsgHeader header, TerminalReplyMsgBody msgBody) {
        return null;
    }
    @Override
    public Integer getMsgId() {
        return Jt808MsgType.CLIENT_COMMON_REPLY.getMsgId();
    }
}
