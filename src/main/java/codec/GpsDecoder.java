package codec;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import packet.GpsRequest;
import util.BinaryUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class GpsDecoder extends ByteToMessageDecoder{
	
	private static final Logger LOG = LoggerFactory.getLogger(GpsDecoder.class);
	
	private boolean verify(ByteBuf buf, int loops){
		byte result = -1;
		for(int i = 1; i <= loops; i++){
			if(i == 1)
				result = buf.getByte(i);
			else
				result ^= buf.getByte(i);
		}
		if(LOG.isDebugEnabled())
			LOG.debug(("vefiry: " + BinaryUtil.BinaryToHexString(new byte[]{result})));
		return result == buf.getByte(loops + 1);
	}
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf buf,
			List<Object> out) throws Exception {
		
		if (buf.readableBytes() < 5) {
			return;
		}
		
		int readable = buf.readableBytes();
		byte[] msg = new byte[readable];
		
		for(int i = 0; i < msg.length; i++){
			msg[i] = buf.getByte(i);
		}
		
		String strMsg = BinaryUtil.BinaryToHexString(msg);
		LOG.info(strMsg);
		
		int mark 		= buf.readByte();
		int id 			= buf.readShort();
		int len 		= buf.readShort();
		
		if(id == 258){
			if(buf.readableBytes() < 10){
				int currentIndex = buf.readerIndex();
				buf.readerIndex(currentIndex - 5);
				return;
			}
			byte[] discards = new byte[10];
			buf.readBytes(discards);
			out.add(new GpsRequest(id));
			return;
		}
		
		int bodyLen = len + 10;
		if(buf.readableBytes() < bodyLen){
			int currentIndex = buf.readerIndex();
			buf.readerIndex(currentIndex - 5);
			return;
		}
		
		/**
		 * 校验码 
		 */
		if(!verify(buf, len + 12)){
			LOG.error("校验码错误, client ip: " + ctx.channel().remoteAddress());
			byte[] discards = new byte[buf.readableBytes()];
			buf.readBytes(discards);
			return;
		}
		
		byte[] phone = new byte[6];
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
		
		byte[] time = new byte[6];
		for(int i = 0; i < 6; i++){
			time[i] 	= buf.readByte();
		}
		
		GpsRequest request = new GpsRequest(mark, id, len, BinaryUtil.BinaryToHexString_(phone), 
				liushui, alert, gpsStatu, latitudeX, latitudeY, hight, speed, 
				dir, BinaryUtil.BinaryToHexString_(time));
		
		byte[] discards = new byte[len - 26];
		buf.readBytes(discards);
		
		out.add(request);

	}

}
