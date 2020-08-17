package com.youshusoft.gps.jt808server.msg.model;

/**
 * @author hylexus
 * Created At 2020-03-25 6:47 下午
 */
public interface RespMsg {
    Integer getMsgId();
    String getTerminalId();
    Integer getFlowId();
    RespMsgBody getBody();
}
