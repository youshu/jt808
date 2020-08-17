
package com.youshusoft.gps.jt808server.msg.model;

import com.youshusoft.gps.jt808server.core.msgbuf.MsgBuf;
import com.youshusoft.gps.jt808server.core.wrapper.ByteWrapper;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * @author hylexus
 * Created At 2020-01-29 12:03 下午
 */
@Data
public class BatchLocationRequestMsgBody implements RequestMsgBody {
    private ByteWrapper type;
    private List<LocationRequestMsgBody> list;

    @Override
    public void read(RequestMsgHeader header, MsgBuf msgBuf) {
        Integer amount = msgBuf.readWord().toInt();
        this.setType(msgBuf.readByte());
        List<LocationRequestMsgBody> list = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            Integer length = msgBuf.readWord().toInt();
            MsgBuf msgBuf1 = msgBuf.readMsgBuf(length);
            LocationRequestMsgBody locationRequestMsgBody = new LocationRequestMsgBody();
            locationRequestMsgBody.read(header,msgBuf1);
            list.add(locationRequestMsgBody);
        }
        this.setList(list);
    }
}
