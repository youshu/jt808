package com.youshusoft.gps.jt808server.msg.model;

import com.youshusoft.gps.jt808server.core.wrapper.ByteWrapper;
import com.youshusoft.gps.jt808server.core.wrapper.StringWrapper;
import com.youshusoft.gps.jt808server.core.wrapper.WordWrapper;
import lombok.Data;
import lombok.SneakyThrows;

/**
 * @author: 悠树
 * @create: 2020-06-21 17:48
 **/
@Data
public class RegisterRespMsgBody extends AbstractRespMsgBody{
    // 1. 应答流水号 WORD terminal flowId
    private WordWrapper replyFlowId;
    // 3. 结果  byte 0:成功/确认;1:失败;2:消息有误;3:不支持
    private ByteWrapper result;
    private StringWrapper authCode;

    public RegisterRespMsgBody(Integer replyFlowId, byte result, String authCode) {
        this.replyFlowId = WordWrapper.valueOf(replyFlowId);
        this.result = ByteWrapper.valueOf(result);
        this.authCode = StringWrapper.valueOf(authCode);
    }
    @SneakyThrows
    @Override
    public byte[] toBytes() {
        return super.toBytes(replyFlowId,result,authCode);
    }


    @Override
    public Integer replyMsgType() {
        return Jt808MsgType.CLIENT_REGISTER_REPLY.getMsgId();
    }
}
