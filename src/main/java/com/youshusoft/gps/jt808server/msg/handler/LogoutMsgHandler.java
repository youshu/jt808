package com.youshusoft.gps.jt808server.msg.handler;

import com.youshusoft.gps.jt808server.msg.model.*;

/**
 * 鉴权
 * @author: 悠树
 * @create: 2020-06-21 17:50
 **/
public class LogoutMsgHandler extends AbstractJt808RequestMsgHandler<EmptyRequestMsgBody> {

    @Override
    protected RespMsgBody handle(RequestMsgHeader header, EmptyRequestMsgBody authRequestMsgBody) {
        return CommonRespMsgBody.success(header.getFlowId(),getMsgId());
    }

    @Override
    public Integer getMsgId() {
        return Jt808MsgType.CLIENT_LOG_OUT.getMsgId();
    }
}
