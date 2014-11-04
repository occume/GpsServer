package test;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class SendMessageTest {

	public static void main(String[] args) throws InterruptedException {
		String src = "7E0200003101343961013914E70000000000000002026104FF06EF65DE0000000000001403031024450104000000310202000003020000E00104E10204D3DC7E";
		byte[] ret = HexStringToBinary(src);
		System.out.println(ret.length);
		
		EventLoopGroup worker = new NioEventLoopGroup(1);
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
		
		Channel c = b.connect("127.0.0.1", 10086).sync().channel();

		for(int i = 0; i < 1; i++){
				c.writeAndFlush(c.alloc().buffer().writeBytes(ret));
		}
	}
	
	private static String hexStr =  "0123456789ABCDEF";  
    private static String[] binaryArray =   
        {"0000","0001","0010","0011",  
        "0100","0101","0110","0111",  
        "1000","1001","1010","1011",  
        "1100","1101","1110","1111"}; 
	
	public static byte[] HexStringToBinary(String hexString){  
        //hexString的长度对2取整，作为bytes的长度  
        int len = hexString.length()/2;  
        byte[] bytes = new byte[len];  
        byte high = 0;//字节高四位  
        byte low = 0;//字节低四位  
  
        for(int i=0;i<len;i++){  
             //右移四位得到高位  
             high = (byte)((hexStr.indexOf(hexString.charAt(2*i)))<<4);  
             low = (byte)hexStr.indexOf(hexString.charAt(2*i+1));  
             bytes[i] = (byte) (high|low);//高地位做或运算  
        }  
        return bytes;  
    }  

}
