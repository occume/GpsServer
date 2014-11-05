package test;

import util.BinaryUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ThroughputTest {
	
	public static void main(String...strings){
	
		for(int i = 0; i < 1000; i++){
			new Thread(new Runnable() {
				public void run() {
					try {
						new Client1().start();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
		
	}
	
}

class Client1{
	
	public void start() throws InterruptedException{
		EventLoopGroup worker = new NioEventLoopGroup(1);
		
		Bootstrap b = new Bootstrap();
		b.group(worker)
		 .channel(NioSocketChannel.class)
		 .option(ChannelOption.TCP_NODELAY, true)
		 .option(ChannelOption.SO_SNDBUF, 1)
		 
		 .handler(new ChannelInitializer<SocketChannel>() {
			protected void initChannel(SocketChannel ch) throws Exception {
			}
		});
		
		Channel c = b.connect("127.0.0.1", 8888).sync().channel();
		String src = "7E0200003101343961013914E70000000000000002026104FF06EF65DE0000000000001403031024450104000000310202000003020000E00104E10204D3DC7E";
		byte[] ret = BinaryUtil.HexStringToBinary(src);

		for(int i = 0; i < 1000; i++){
			c.writeAndFlush(c.alloc().buffer().writeBytes(ret));
		}
	}
	
}
