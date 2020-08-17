package com.youshusoft.gps.jt808server.msg.model;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: 悠树
 * @create: 2020-06-21 17:22
 **/
@Data
public class RespMsgMetadata implements RespMsg{
    private static final AtomicInteger FLOW_ID = new AtomicInteger(1);

    public RespMsgMetadata() {
        this(FLOW_ID.getAndIncrement() % Integer.MAX_VALUE);
    }

    public RespMsgMetadata(Integer flowId) {
        this.flowId = flowId;
    }

    private RespMsgBody body;
    private Integer msgId;
    private String terminalId;
    private Integer flowId;

}
