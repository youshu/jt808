package com.youshusoft.gps.jt808server.msg.handler;


import com.youshusoft.gps.jt808server.netty.Session;

public abstract class AbstractInMessageHandler<I, R,M> implements MessageHandler {
    /**
     * 设备数据处理
     * @param inData
     * @return
     */
    public void doHandle(I inData, Session session){
        M m = decode(inData);
        if(m == null){
            return;
        }
        R r = handle(inData,m);
        if(r != null){
            session.out(r);
        }
        
    }

    /**
     * 数据解码
     * @param inData
     * @return
     */
    protected abstract M decode(I inData);

    /**
     * 业务处理
     * @param inData
     * @param m
     * @return
     */
    protected abstract R handle(I inData, M m);
}
