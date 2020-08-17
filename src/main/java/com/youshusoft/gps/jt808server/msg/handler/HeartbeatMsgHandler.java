package com.youshusoft.gps.jt808server.msg.handler;

import com.youshusoft.gps.jt808server.msg.model.*;

/**
 * 心跳
 * @author: 悠树
 * @create: 2020-06-21 17:50
 **/
public class HeartbeatMsgHandler extends AbstractJt808RequestMsgHandler<EmptyRequestMsgBody> {

    @Override
    protected RespMsgBody handle(RequestMsgHeader header, EmptyRequestMsgBody requestMsgBody) {
        return CommonRespMsgBody.success(header.getFlowId(),getMsgId());
    }
    @Override
    public Integer getMsgId() {
        return Jt808MsgType.CLIENT_HEART_BEAT.getMsgId();
    }
}
