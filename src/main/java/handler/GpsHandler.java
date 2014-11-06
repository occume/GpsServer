package handler;

import java.util.concurrent.atomic.AtomicInteger;

import manage.Sharding;
import manage.World;
import mybatis.domain.TrackBean;
import mybatis.domain.User;
import mybatis.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import codec.GpsDecoder;
import packet.GpsRequest;
import session.Session;
import util.Stopwatch;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@Component
@Scope("prototype")
public class GpsHandler extends SimpleChannelInboundHandler<GpsRequest>{

	private AtomicInteger count = new AtomicInteger();
	
	private Stopwatch sw;
	
	private Session session;
	
	@Autowired
	UserService userService;
	
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
			LOG.info("Heatbeat message, client: " + ctx.channel().remoteAddress());
		}
		else{
			if(!Sharding.instance().exist(msg.getSimId())){
				LOG.info("create a new table: " + msg.getSimId());
				userService.createTable("TB_" + msg.getSimId());
				Sharding.instance().put(msg.getSimId());
			}
			userService.addTrack(new TrackBean(msg.getLatitudeX(), msg.getLatitudeY()));
		}
		
		LOG.info(msg.toString());
		
		if(count.compareAndSet(1000000, 0)){
			LOG.info("1000000 messages cost: " + sw.longTime());
		}
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		ctx.close();
		World.instance().removeChannel(ctx.channel());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
//		cause.printStackTrace();
		LOG.error(cause.getMessage());
		ctx.close();
		
	}

}
