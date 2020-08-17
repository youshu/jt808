package com.youshusoft.gps.jt808server.msg.handler;

import com.youshusoft.gps.jt808server.MyApplication;
import com.youshusoft.gps.jt808server.msg.model.*;

/**
 * ICCID上报
 * @author: 悠树
 * @create: 2020-06-21 17:50
 **/
public class IccidMsgHandler extends AbstractJt808RequestMsgHandler<IccidRequestMsgBody> {

    @Override
    protected RespMsgBody handle(RequestMsgHeader header, IccidRequestMsgBody msgBody) {
        return new IccidRespMsgBody();
    }
    @Override
    public Integer getMsgId() {
        return Jt808MsgType.ICCOD_UPLOAD.getMsgId();
    }
}
