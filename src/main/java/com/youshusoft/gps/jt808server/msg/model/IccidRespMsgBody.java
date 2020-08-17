package com.youshusoft.gps.jt808server.msg.model;

import lombok.Data;

/**
 * @author: 悠树
 * @create: 2020-06-21 22:00
 **/
@Data
public class IccidRespMsgBody extends AbstractRespMsgBody {


    @Override
    public byte[] toBytes() {
        return new byte[0];
    }

    @Override
    public Integer replyMsgType() {
        return Jt808MsgType.ICCOD_UPLOAD_REPLY.getMsgId();
    }
}
