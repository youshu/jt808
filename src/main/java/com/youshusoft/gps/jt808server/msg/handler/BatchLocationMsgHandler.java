package com.youshusoft.gps.jt808server.msg.handler;

import com.youshusoft.gps.jt808server.msg.model.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: 悠树
 * @create: 2020-06-21 17:50
 **/
@Slf4j
public class BatchLocationMsgHandler extends AbstractJt808RequestMsgHandler<BatchLocationRequestMsgBody> {

    @Override
    protected RespMsgBody handle(RequestMsgHeader header, BatchLocationRequestMsgBody msgBody) {
        return CommonRespMsgBody.success(header.getFlowId(),getMsgId());
    }
    @Override
    public Integer getMsgId() {
        return Jt808MsgType.CLIENT_BATCH_LOCATION_INFO_UPLOAD.getMsgId();
    }
}
