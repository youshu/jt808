package com.youshusoft.gps.jt808server.netty;

import com.youshusoft.gps.jt808server.core.codec.BytesEncoder;
import com.youshusoft.gps.jt808server.core.codec.Encoder;
import com.youshusoft.gps.jt808server.msg.model.RespMsgMetadata;
import com.youshusoft.gps.jt808server.util.HexStringUtils;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Data
@Slf4j
public class NettySession implements Session<RespMsgMetadata> {
    private static Encoder encoder = new Encoder(new BytesEncoder.DefaultBytesEncoder());
    public NettySession(String terminalId, Channel channel) {
        this.id = channel.id().asLongText();
        this.terminalId = terminalId;
        this.channel = channel;
        this.lastActivityTime = System.currentTimeMillis();
    }
    private String id;
    private String terminalId;
    private Channel channel;
    private Long lastActivityTime;
    @Override
    public void out(RespMsgMetadata respMsgMetadata) {
        byte[] bytes;
        try {
            bytes = encoder.encodeRespMsg(respMsgMetadata);
        } catch (IOException e) {
            log.error("encodeRespMsg err",e);
            return;
        }
        String hexString = HexStringUtils.bytes2HexString(bytes);
        log.info("send msg {}", hexString);
        ChannelFuture cf = channel.writeAndFlush(Unpooled.wrappedBuffer(bytes));
        cf.addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if(future.isDone() && future.isSuccess()){
                    log.info("writeAndFlush succ");
                }else{
                    log.info("writeAndFlush fail {}",future.cause());
                }
            }
        });
        
        
    }
    public static String generateSessionId(Channel channel) {
        return channel.id().asLongText();
    }

    public static NettySession buildSession(Channel channel, String terminalId) {
        NettySession session = new NettySession(terminalId,channel);
        return session;
    }
    @Override
    public boolean isActive() {
        return channel.isActive();
//        return channel.isActive() && System.currentTimeMillis() - lastActivityTime < 1200000;
    }
}
