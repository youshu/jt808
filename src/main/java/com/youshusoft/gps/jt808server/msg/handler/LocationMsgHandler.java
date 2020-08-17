package com.youshusoft.gps.jt808server.msg.handler;

import com.youshusoft.gps.jt808server.msg.model.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: 悠树
 * @create: 2020-06-21 17:50
 **/
@Slf4j
public class LocationMsgHandler extends AbstractJt808RequestMsgHandler<LocationRequestMsgBody> {

    @Override
    protected RespMsgBody handle(RequestMsgHeader header, LocationRequestMsgBody msgBody) {
        return CommonRespMsgBody.success(header.getFlowId(),getMsgId());
    }
    @Override
    public Integer getMsgId() {
        return Jt808MsgType.CLIENT_LOCATION_INFO_UPLOAD.getMsgId();
    }
}
