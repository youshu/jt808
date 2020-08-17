package com.youshusoft.gps.jt808server.msg.model;

import com.youshusoft.gps.jt808server.core.msgbuf.MsgBuf;
import com.youshusoft.gps.jt808server.core.wrapper.ByteWrapper;
import com.youshusoft.gps.jt808server.core.wrapper.WordWrapper;
import lombok.Data;

@Data
public class TerminalReplyMsgBody implements RequestMsgBody {
    private WordWrapper replyFlowId;
    private WordWrapper replyMsgId;
    private ByteWrapper result;

    @Override
    public void read(RequestMsgHeader header, MsgBuf msgBuf) {
        this.setReplyFlowId(msgBuf.readWord());
        this.setReplyMsgId(msgBuf.readWord());
        this.setResult(msgBuf.readByte());
    }
}
