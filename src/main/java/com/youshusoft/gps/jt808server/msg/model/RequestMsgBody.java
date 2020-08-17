package com.youshusoft.gps.jt808server.msg.model;

import com.youshusoft.gps.jt808server.core.msgbuf.MsgBuf;

/**
 * A marker interface for request msg body.
 *
 * @author hylexus
 * Created At 2019-09-19 10:51 下午
 */
public interface RequestMsgBody {
    /**
     * 从msgBuf读取数据
     * @param header
     * @param msgBuf
     */
    void read(RequestMsgHeader header, MsgBuf msgBuf);

}
