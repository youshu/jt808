package com.youshusoft.gps.jt808server.msg.model;

import com.youshusoft.gps.jt808server.core.msgbuf.MsgBuf;
import com.youshusoft.gps.jt808server.core.wrapper.ByteArrayWrapper;
import com.youshusoft.gps.jt808server.core.wrapper.WordWrapper;
import lombok.Data;

/**
 * @author: 悠树
 * @create: 2020-06-21 17:48
 **/
@Data
public class RegisterRequestMsgBody implements RequestMsgBody{
    private WordWrapper province;
    private WordWrapper city;
    private ByteArrayWrapper terminalId;

    @Override
    public void read(RequestMsgHeader header, MsgBuf msgBuf) {
        this.setProvince(msgBuf.readWord());
        this.setCity(msgBuf.readWord());
        this.setTerminalId(msgBuf.getByteArray(17,7));
    }
}
