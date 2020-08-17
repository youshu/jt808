package com.youshusoft.gps.jt808server.msg.model;

import com.youshusoft.gps.jt808server.core.wrapper.ByteWrapper;
import com.youshusoft.gps.jt808server.core.wrapper.WordWrapper;
import lombok.Data;

import java.io.IOException;

/**
 * @author: 悠树
 * @create: 2020-06-21 22:00
 **/
@Data
public class CommonRespMsgBody extends AbstractRespMsgBody {

    // 1. 应答流水号 WORD terminal flowId
    private WordWrapper replyFlowId;
    // 2. 应答id WORD 0x0102 ...
    private WordWrapper replyMsgId;
    // 3. 结果  byte 0:成功/确认;1:失败;2:消息有误;3:不支持
    private ByteWrapper result ;

    protected CommonRespMsgBody() {
    }
    public static CommonRespMsgBody success(Integer replyFlowId, Integer replyFor) {
        return of(SUCCESS, replyFlowId, replyFor);
    }
    public static CommonRespMsgBody failure(Integer replyFlowId, Integer replyFor) {
        return of(FAILURE, replyFlowId, replyFor);
    }
    public static CommonRespMsgBody of(byte result, Integer replyFlowId, Integer replyFor) {
        CommonRespMsgBody body = new CommonRespMsgBody();
        body.setReplyFlowId(WordWrapper.valueOf(replyFlowId));
        body.setReplyMsgId(WordWrapper.valueOf(replyFor));
        body.setResult(ByteWrapper.valueOf(result));
        return body;   
    }

    @Override
    public byte[] toBytes() throws IOException {
        return toBytes(replyFlowId,replyMsgId,result);
    }

    @Override
    public Integer replyMsgType() {
        return Jt808MsgType.SERVER_COMMON_REPLY.getMsgId();
    }
}
