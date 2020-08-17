package com.youshusoft.gps.jt808server.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hylexus
 * Created At 2019-08-21 21:48
 */
@Slf4j
public class HeatBeatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (!(evt instanceof IdleStateEvent)) {
            super.userEventTriggered(ctx, evt);
            return;
        }

        if (((IdleStateEvent) evt).state() == IdleState.READER_IDLE) {
            log.debug("disconnected idle connection");
            NettySessionManager.getInstance().removeByChannel(ctx.channel());
        }
    }
}
