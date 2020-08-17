package com.youshusoft.gps.jt808server.msg.model;

import com.youshusoft.gps.jt808server.core.msgbuf.MsgBuf;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author hylexus
 * Created At 2019-09-19 11:35 下午
 */
@Data
@Accessors(chain = true)
public class RequestMsgMetadata implements RequestMsg {
    protected RequestMsgHeader header;
    protected MsgBuf body;
    protected byte checkSum;

    @Override
    public String toString() {
        return "RequestMsgMetadata{"
                + ", header=" + header
                //+ ", bodyBytes=" + Arrays.toString(bodyBytes)
                + ", checkSum=" + checkSum
                + '}';
    }
}
