package com.youshusoft.gps.jt808server.msg.handler;

import com.youshusoft.gps.jt808server.msg.model.*;

/**
 * @author: 悠树
 * @create: 2020-06-21 17:50
 **/
public class RegisterMsgHandler extends AbstractJt808RequestMsgHandler<RegisterRequestMsgBody> {

    @Override
    protected RespMsgBody handle(RequestMsgHeader header, RegisterRequestMsgBody registerRequestMsgBody) {
        RegisterRespMsgBody respMsgBody = new RegisterRespMsgBody(header.getFlowId(),RegisterRespMsgBody.SUCCESS, header.getTerminalId());
        return respMsgBody;
    }
    @Override
    public Integer getMsgId() {
        return Jt808MsgType.CLIENT_REGISTER.getMsgId();
    }
}
