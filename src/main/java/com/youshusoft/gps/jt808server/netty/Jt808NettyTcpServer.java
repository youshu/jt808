package com.youshusoft.gps.jt808server.netty;

import com.youshusoft.gps.jt808server.core.codec.Decoder;
import com.youshusoft.gps.jt808server.msg.handler.Jt808HandlerFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

import static com.youshusoft.gps.jt808server.core.JtConstant.*;

@Slf4j
public class Jt808NettyTcpServer {
	private int port;
	private EventLoopGroup bossGroup = new NioEventLoopGroup(5);
	EventLoopGroup workerGroup = new NioEventLoopGroup(20);
	public Jt808NettyTcpServer(int port) {
		this.port = port;
	}

	public void start() throws InterruptedException {
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		
		 serverBootstrap.option(ChannelOption.SO_BACKLOG, 2048)
				.option(ChannelOption.SO_REUSEADDR, true)
				.childOption(ChannelOption.SO_KEEPALIVE, true);
		 
		serverBootstrap.group(bossGroup, workerGroup);
		serverBootstrap.channel(NioServerSocketChannel.class);
		
		ChannelInitializer<SocketChannel> channelChannelInitializer = new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel channel) throws Exception {
				ChannelPipeline pipeline = channel.pipeline();
				pipeline.addLast(NETTY_HANDLER_NAME_808_IDLE_STATE, new IdleStateHandler(40, 0, 0, TimeUnit.MINUTES));
				pipeline.addLast(NETTY_HANDLER_NAME_808_HEART_BEAT, new HeatBeatHandler());
				pipeline.addLast(
						NETTY_HANDLER_NAME_808_FRAME,
						new DelimiterBasedFrameDecoder(
								MAX_PACKAGE_LENGTH,
								Unpooled.copiedBuffer(new byte[]{PACKAGE_DELIMITER}),
								Unpooled.copiedBuffer(new byte[]{PACKAGE_DELIMITER, PACKAGE_DELIMITER})
						)
				);
				pipeline.addLast(NETTY_HANDLER_NAME_808_MSG_DISPATCHER_ADAPTER, new ServerHandler(new Decoder(),new Jt808HandlerFactory()));
			}
		};
		serverBootstrap.childHandler(channelChannelInitializer);

		// 服务器绑定端口监听
		ChannelFuture channelFuture  = serverBootstrap.bind(port).sync();
		log.info("{} started successfully，port {}","Jt808NettyTcpServer",port);
		channelFuture.channel().closeFuture().sync();
	}
	public synchronized void stopServer() throws Exception {
		Future<?> future = this.workerGroup.shutdownGracefully().await();
		if (!future.isSuccess()) {
			log.error("<---- netty workerGroup cannot be stopped", future.cause());
		}

		future = this.bossGroup.shutdownGracefully().await();
		if (!future.isSuccess()) {
			log.error("<---- netty bossGroup cannot be stopped", future.cause());
		}
	}

}
