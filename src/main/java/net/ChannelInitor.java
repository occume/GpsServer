package net;

import codec.GpsDecoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class ChannelInitor extends ChannelInitializer<SocketChannel> {
	
	public ChannelInitor(){}

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline().addLast(new GpsDecoder());
	}
	
}