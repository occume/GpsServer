package codec;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.BinaryUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class GpsDecoder extends ByteToMessageDecoder{
	
	private static final Logger LOG = LoggerFactory.getLogger(GpsDecoder.class);

	private boolean verify(ByteBuf buf){
		byte result = -1;
		for(int i = 1; i <= 61; i++){
			if(i == 1)
				result = buf.getByte(i);
			else
				result ^= buf.getByte(i);
		}
		if(LOG.isDebugEnabled())
			LOG.debug(("vefiry: " + BinaryUtil.BinaryToHexString(new byte[]{result})));
		return result == buf.getByte(62);
	}
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf buf,
			List<Object> out) throws Exception {
		
		if (buf.readableBytes() < 64) {
			return;
		}
		/**
		 * 验证检验位
		 */
		if(!verify(buf)){
			LOG.error("校验位检验失败");
			return;
		}
		
		int mark 		= buf.readByte();
		int id 			= buf.readShort();
		int len 		= buf.readShort();
		
		byte[] phone = new byte[6];
		for(int i = 0; i < 6; i++){
			phone[i] 	= buf.readByte();
		}
		
		byte[] time = new byte[6];
		for(int i = 0; i < 6; i++){
			phone[i] 	= buf.readByte();
		}
		
		int liushui 	= buf.readShort();
		int alert 		= buf.readInt();
		int gpsStatu 	= buf.readInt();
		int latitudeX 	= buf.readInt();
		int latitudeY 	= buf.readInt();
		int hight 		= buf.readShort();
		int speed 		= buf.readShort();
		int dir 		= buf.readShort();
		
		System.out.println(buf.order());
		System.out.println("mark = " + mark);
		System.out.println("id = " + id);
		System.out.println("len = " + len);
		System.out.println("phone = " + BinaryUtil.BinaryToHexString(phone));
		System.out.println("ls = " + liushui);
		buf.readerIndex(buf.readableBytes());
		System.out.println(buf.readByte() == mark);
	}

}
