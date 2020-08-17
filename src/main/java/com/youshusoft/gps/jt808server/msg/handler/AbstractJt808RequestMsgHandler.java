package com.youshusoft.gps.jt808server.msg.handler;

import com.youshusoft.gps.jt808server.core.msgbuf.MsgBuf;
import com.youshusoft.gps.jt808server.core.exception.MsgHandleException;
import com.youshusoft.gps.jt808server.msg.model.*;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@Slf4j
public abstract class AbstractJt808RequestMsgHandler<M extends RequestMsgBody> extends AbstractInMessageHandler<RequestMsg, RespMsg, M> implements Jt808MsgHandler{
    private Class c;
    public AbstractJt808RequestMsgHandler() {
        Type type = ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        c = (Class) type;
    }
    @Override
    protected M decode(RequestMsg requestMsg) {
        MsgBuf msgBuf = requestMsg.getBody();
        M m;
        try {
            m = (M) c.newInstance();
        } catch (Exception e) {
            throw new MsgHandleException(e);
        }
        m.read(requestMsg.getHeader(),msgBuf);
        return m;
    }
    
    @Override
    protected RespMsg handle(RequestMsg inData, M m){
        RequestMsgHeader header = inData.getHeader();
        RespMsgBody respMsgBody = handle(header,m);
        if(respMsgBody == null){
            return null;
        }
        RespMsgMetadata respMsg = new RespMsgMetadata();
        respMsg.setBody(respMsgBody);
        respMsg.setTerminalId(header.getTerminalId());
        respMsg.setMsgId(respMsgBody.replyMsgType());
        return respMsg;
    }
    protected abstract RespMsgBody handle(RequestMsgHeader header, M m);
    public abstract Integer getMsgId();
}