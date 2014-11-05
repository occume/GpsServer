package net;

import manage.EventLoopGroups;
import manage.World;
import codec.GpsDecoder;
import handler.GpsHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class ChannelInitor extends ChannelInitializer<SocketChannel> {
	
	public ChannelInitor(){}
	
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		
		World.instance().addChannel(ch);
		
		ch.pipeline().addLast(new GpsDecoder());
		ch.pipeline().addLast(EventLoopGroups.defaultEventLoopGroup(), new GpsHandler());
	}
	
}