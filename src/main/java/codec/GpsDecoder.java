package codec;

import java.util.Date;
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
	
	/**
	 * 验证校验码
	 * @param buf
	 * @param loops
	 * @return
	 */
	private boolean verify(ByteBuf packet){
//		System.out.println("packet capcity: " + packet.readableBytes());
//		ByteBuf packet = check7e(buf);
//		System.out.println("after check lenght: " + packet.readableBytes());
//		System.out.println(BinaryUtil.ByteBufToHexString(packet));
		int capcity = packet.readableBytes();
		int start = 1;
		int checkCodeMark = capcity - 2;
		byte result = -1;
		for(int i = start; i < checkCodeMark; i++){
			if(i == start)
				result = packet.getByte(i);
			else{
				result ^= packet.getByte(i);
				if(i == capcity - 3){
//					System.out.println("last: " + packet.getByte(i));
				}
			}
		}
		
		if(LOG.isDebugEnabled())
			LOG.debug(("vefiry: " + BinaryUtil.BinaryToHexString(new byte[]{result})));
//		System.out.println("result: " + result);
//		System.out.println("checkCode: " + packet.getByte(checkCodeMark));
		return result == packet.getByte(checkCodeMark);
	}
	
	private ByteBuf check7e(ByteBuf buf) {
		
		ByteBuf ret = buf.alloc().buffer();
		int readable = buf.readableBytes();
		
		for(int i = 0; i < readable; i++){
			
			byte b = buf.getByte(i);
			if(b == 0x7D){
				byte next = buf.getByte(i + 1);
//				System.out.println("next: " + next);
				if(next == 0x01){
					ret.writeByte((byte)125);
				}
				else if(next == 0x02){
					ret.writeByte((byte)126);
				}
				i++;
			}
			else{
				ret.writeByte(b);
			}
			
		}
		
		return ret;
	}
	
	private int integrity(ByteBuf buf){
		if(buf.getByte(0) != 0x7E){
			return -1;
		}
		int end = buf.writerIndex();
		int start = buf.readerIndex() + 1;
		int len = 1;
		for(int i = start; i < end; i++){
			len++;
			if(buf.getByte(i) == 0x7E){
				return len;
			}
		}
		return -1;
	}
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf all,
			List<Object> out) throws Exception {
		
//		LOG.info(BinaryUtil.ByteBufToHexString(buf));
//		System.out.println("readerIndex: " + all.readerIndex());
//		System.out.println("writerIndex: " + all.writerIndex());

		int capcity = integrity(all);
		if(capcity == -1){
			return;
		}
//		System.out.println("capcity: " + capcity);
		ByteBuf packet = all.copy(all.readerIndex(), capcity);
		LOG.info(BinaryUtil.ByteBufToHexString(packet));
		byte[] discards = new byte[capcity];
		all.readBytes(discards);
		
		ByteBuf buf = check7e(packet);
//		int mark 		= buf.readByte();
		int id 			= buf.getShort(1);
		
		if(id == 258){
			discards = new byte[buf.readableBytes()];
			buf.readBytes(discards);
			out.add(new GpsRequest(id));
			return;
		}
		
		/**
		 * 校验码 
		 */
		if(!verify(buf)){
			LOG.error("校验码错误, client ip: " + ctx.channel().remoteAddress());
//			byte[] discards = new byte[capcity - 3];
//			buf.readBytes(discards);
//			return;
		}
//	
//		System.out.println("---readerIndex " + buf.readerIndex());
						  buf.readByte();
						  buf.readShort();
		int len 		= buf.readShort();
		
//		int bodyLen = len + 10;
//		if(buf.readableBytes() < bodyLen){
//			int currentIndex = buf.readerIndex();
//			buf.readerIndex(currentIndex - 5);
//			return;
//		}
		
		byte[] phone = new byte[6];
		for(int i = 0; i < 6; i++){
			phone[i] 	= buf.readByte();
		}
		
		int liushui 	= buf.readShort();
		int alarmFlag 	= buf.readInt();
		int gpsStatu 	= buf.readInt();
		int longitude	= buf.readInt();
		int latitude 	= buf.readInt();
		int height 		= buf.readShort();
		int gpsSpeed 	= buf.readShort();
		int angle 		= buf.readShort();
//		System.out.println("alarmFlag: " + alarmFlag);
//		System.out.println("gpsStatu: " + gpsStatu);
//		System.out.println("longitude: " + longitude);
//		System.out.println("latitude: " + latitude);
		byte[] time = new byte[6];
		for(int i = 0; i < 6; i++){
			time[i] 	= buf.readByte();
		}
//		System.out.println("---readerIndex " + buf.readerIndex());
		String simId = BinaryUtil.BinaryToHexString_(phone);
		LOG.info("time: " + BinaryUtil.BinaryToHexString_(time));
		GpsRequest request = new GpsRequest(id, len, simId, liushui, 
				longitude, latitude, gpsSpeed, gpsSpeed, angle, alarmFlag, 
				height, gpsStatu, 0, 0, 0, new Date());
		
//		discards = new byte[capcity - 41];
//		buf.readBytes(discards);
		buf.release();
		out.add(request);

	}

}
