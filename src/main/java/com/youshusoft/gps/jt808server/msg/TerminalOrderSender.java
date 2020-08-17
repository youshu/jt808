package com.youshusoft.gps.jt808server.msg;

import com.youshusoft.gps.jt808server.core.wrapper.DwordWrapper;
import com.youshusoft.gps.jt808server.msg.model.*;
import com.youshusoft.gps.jt808server.netty.NettySession;
import com.youshusoft.gps.jt808server.netty.NettySessionManager;

import java.util.Optional;

/**
 * @author: 悠树
 * @create: 2020-07-08 19:13
 **/
public class TerminalOrderSender {
    
    public static void restart(String terminalId){
       send(terminalId,new TerminalOrderRespMsgBody(TerminalOrderRespMsgBody.RESTART));
    }
    public static void query(String terminalId){
        send(terminalId,new QueryRespMsgBody());
    }
    public static void reportingInterval(String terminalId,int interval){
        TerminalSettingRespMsgBody respMsgBody = new TerminalSettingRespMsgBody(
                new TerminalSettingRespMsgBody.SettingItem(
                        TerminalSettingRespMsgBody.SettingItem.REPORTING_INTERVAL, DwordWrapper.valueOf(interval)
                )
        );
        send(terminalId,respMsgBody);
    }
    
    public static void send(String terminalId,RespMsgBody respMsgBody){
        RespMsgMetadata respMsgMetadata = new RespMsgMetadata();
        respMsgMetadata.setTerminalId(terminalId);
        respMsgMetadata.setBody(respMsgBody);
        respMsgMetadata.setMsgId(respMsgBody.replyMsgType());
        send(respMsgMetadata);
    }
    public static void send(RespMsgMetadata respMsgMetadata){
        Optional<NettySession> optional = NettySessionManager.getInstance().findByTerminalId(respMsgMetadata.getTerminalId().toString());
        if(optional.isPresent()){
            optional.get().out(respMsgMetadata);
        }
    }
}
