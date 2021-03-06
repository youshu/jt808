package com.youshusoft.gps.jt808server.msg.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author hylexus
 * Created At 2019-09-19 11:49 下午
 */
@Data
@Accessors(chain = true)
public class RequestMsgWrapper {
    private RequestMsgMetadata metadata;
    private RequestMsgBody body;
}
