package handler;

import java.util.concurrent.atomic.AtomicInteger;

import manage.World;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import codec.GpsDecoder;
import packet.GpsRequest;
import session.Session;
import util.Stopwatch;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@Component
public class GpsHandler extends SimpleChannelInboundHandler<GpsRequest>{

	private AtomicInteger count = new AtomicInteger();
	
	private Stopwatch sw;
	
	private Session session;
	
	private static final Logger LOG = LoggerFactory.getLogger(GpsDecoder.class);
	
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		session = new Session(ctx.channel());
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, GpsRequest msg)
			throws Exception {
		
		if(count.compareAndSet(0, 1)){
			sw = Stopwatch.newStopwatch();
		}
		else{
			count.incrementAndGet();
		}
		
		if(msg.getId() == 258){
			LOG.info("Hearbeat message, client: " + ctx.channel().remoteAddress());
		}
		
		LOG.info(msg.toString());
		
		if(count.compareAndSet(1000000, 0)){
			LOG.info("1000000 messages cost: " + sw.longTime());
		}
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		World.instance().removeChannel(ctx.channel());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
//		LOG.error(cause.getMessage());
	}

}
