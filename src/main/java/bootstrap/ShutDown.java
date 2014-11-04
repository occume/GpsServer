package bootstrap;

import util.Conf;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ShutDown {

	public static void main(String[] args) throws InterruptedException {
		
		final EventLoopGroup worker = new NioEventLoopGroup(1);
		Bootstrap b = new Bootstrap();
		b.group(worker)
		 .channel(NioSocketChannel.class)
		 .option(ChannelOption.TCP_NODELAY, true)
		 .option(ChannelOption.SO_SNDBUF, 1)
		 
		 .handler(new ChannelInitializer<SocketChannel>() {
			protected void initChannel(SocketChannel ch) throws Exception {
//				ch.pipeline().addLast(new StringEncoder());
			}
		});
		
		ChannelFuture future = b.connect("127.0.0.1", 10087);
		future.addListener(new ChannelFutureListener() {
	        @Override
	        public void operationComplete(ChannelFuture future) {
	        	worker.shutdownGracefully();
	            future.channel().close();
	        }
	    });
		
	}

}
