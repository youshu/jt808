package com.youshusoft.gps.jt808server.msg.handler;

import com.youshusoft.gps.jt808server.MyApplication;
import com.youshusoft.gps.jt808server.msg.model.*;

/**
 * 鉴权
 * @author: 悠树
 * @create: 2020-06-21 17:50
 **/
public class AuthMsgHandler extends AbstractJt808RequestMsgHandler<AuthRequestMsgBody> {

    @Override
    protected RespMsgBody handle(RequestMsgHeader header, AuthRequestMsgBody authRequestMsgBody) {
        //TODO 鉴权逻辑
        return CommonRespMsgBody.success(header.getFlowId(),getMsgId());
    }

    @Override
    public Integer getMsgId() {
        return Jt808MsgType.CLIENT_AUTH.getMsgId();
    }
}
