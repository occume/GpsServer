package net;


import java.util.Date;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;
import manage.EventLoopGroups;
import net.NamedThreadFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import util.Conf;
import util.Constants;

@Component
public class GpsServer{

	private static final Logger LOG = LoggerFactory.getLogger(GpsServer.class);
	
	private ServerBootstrap b;
	
	private NioEventLoopGroup boss = new NioEventLoopGroup(2, new NamedThreadFactory("BOSS"));
	private NioEventLoopGroup worker = new NioEventLoopGroup(2, new NamedThreadFactory("WORKER"));
	
	private static int SERVER_PROT = Conf.getInt(Constants.ConfKey.SERVER_PORT);
	
	public static final ChannelGroup ALL_CHANNELS = new DefaultChannelGroup("CHANNELS", GlobalEventExecutor.INSTANCE);
	
	public void start()
	{
		
		try {
			
			b = new ServerBootstrap();
			b.group(boss, worker)
			 .channel(NioServerSocketChannel.class)
			 .childHandler(new ChannelInitor())
			 .option(ChannelOption.SO_BACKLOG, 128)
			 .option(ChannelOption.TCP_NODELAY, true)
			 .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
			 .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
             .childOption(ChannelOption.SO_KEEPALIVE, true);
			
			if(SERVER_PROT == 0)
				SERVER_PROT = 8888;
			Channel serverChannel = b.bind(SERVER_PROT).sync().channel();
			ALL_CHANNELS.add(serverChannel);
			
			LOG.info("=============================================");
			LOG.info("Server start succes!");
			LOG.info("Start at: " + new Date());
			LOG.info("Listen port: " + SERVER_PROT);
			LOG.info("=============================================");
		} catch (InterruptedException e) {
			LOG.error("Server not start");
		}
		
		try {
			
			b = new ServerBootstrap();
			b.group(boss, worker)
			 .channel(NioServerSocketChannel.class)
			 .childHandler(new ChannelInitializer<Channel>() {
				protected void initChannel(Channel ch) throws Exception {
					stop();
				}
			})
             .childOption(ChannelOption.SO_KEEPALIVE, true);
			
			Channel serverChannel = b.bind(10087).sync().channel();
			
			LOG.info("ShutDown port: 10087");
		} catch (InterruptedException e) {
			LOG.error("Server not start");
		}
		
	}

	public void stop() {
		boss.shutdownGracefully();
		worker.shutdownGracefully();
		EventLoopGroups.closeDefaultEventLoopGroup();
		LOG.info("--------------------------------------------");
		LOG.info("Server stop succes!");
		LOG.info("Stop at: " + new Date());
		LOG.info("--------------------------------------------");
	}

}
