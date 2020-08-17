package com.youshusoft.gps.jt808server.netty;

import com.youshusoft.gps.jt808server.MyApplication;
import com.youshusoft.gps.jt808server.core.codec.Decoder;
import com.youshusoft.gps.jt808server.msg.handler.AbstractJt808RequestMsgHandler;
import com.youshusoft.gps.jt808server.msg.handler.Jt808HandlerFactory;
import com.youshusoft.gps.jt808server.msg.model.RequestMsgHeader;
import com.youshusoft.gps.jt808server.msg.model.RequestMsgMetadata;
import com.youshusoft.gps.jt808server.util.HexStringUtils;
import com.youshusoft.gps.jt808server.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class ServerHandler extends SimpleChannelInboundHandler<Object>{
	private static AtomicInteger nConnection = new AtomicInteger();
	private Decoder decoder;
	private Jt808HandlerFactory jt808HandlerFactory;

	public ServerHandler(Decoder decoder, Jt808HandlerFactory jt808HandlerFactory) {
		this.decoder = decoder;
		this.jt808HandlerFactory = jt808HandlerFactory;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg){
		if(msg == null){
			log.info("msg is null");
			return;
		}
		final ByteBuf buf = (ByteBuf) msg;
		int readableBytes = buf.readableBytes();
		if (readableBytes <= 0) {
			log.info("msg readableBytes {}",readableBytes);
			return;
		}
		final byte[] unescaped = new byte[buf.readableBytes()];
		buf.readBytes(unescaped);
		try {
			String hexString = HexStringUtils.bytes2HexString(unescaped);
			log.info("receive msg {}", hexString);
			final byte[] escaped = ProtocolUtils.doEscape4ReceiveJt808Msg(unescaped, 0, unescaped.length);

			final RequestMsgMetadata metadata = decoder.parseMsgMetadata(escaped);
			final RequestMsgHeader header = metadata.getHeader();
			final Integer msgId = header.getMsgId();
			log.info("receive msgId {}",Integer.toHexString(msgId));
			AbstractJt808RequestMsgHandler handler = jt808HandlerFactory.getInProtocolHandler(msgId);
			if(handler == null){
				log.warn("handler not find {}",msgId);
				return;
			}
			NettySession nettySession = NettySessionManager.getInstance().persistenceIfNecessary(header.getTerminalId().toString(),ctx.channel());
			handler.doHandle(metadata,nettySession);
		}catch (Exception e){
			log.error("channelRead0 err",e);
		}
		
		
	}
	/*
     * 覆盖 channelActive 方法 在channel被启用的时候触发 (在建立连接的时候)
     */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
//		LOGGER.info("["+ ctx +"]" + "channelActive:" + ctx.channel().remoteAddress());
		nConnection.incrementAndGet();
		log.info("connections:{}",nConnection.get());
		super.channelActive(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		nConnection.decrementAndGet();
		log.info("connections:{}",nConnection.get());
		super.channelInactive(ctx);
		NettySessionManager.getInstance().removeByChannel(ctx.channel());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		log.info("["+ ctx +"]" + "exceptionCaught:" + ctx.channel().remoteAddress());
		super.exceptionCaught(ctx, cause);
		Channel channel = ctx.channel();
		if(channel.isActive()){
			ctx.close();
		}
	}
}
