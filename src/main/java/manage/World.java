package manage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class World {
	
	private static World world = new World();
	
	private final ChannelGroup ALL_CLIENTS = new DefaultChannelGroup("CHANNELS", GlobalEventExecutor.INSTANCE);
	
	private static final Logger LOG = LoggerFactory.getLogger(World.class);
	
	private World(){}
	
	public static World instance(){
		return world;
	}
	
	public void addChannel(Channel channel){
		ALL_CLIENTS.add(channel);
		LOG.info("new connection:" + channel.remoteAddress() +", total connections: " + size());
	}
	
	public void removeChannel(Channel channel){
		ALL_CLIENTS.remove(channel);
//		LOG.info("A new connection disconnected, total connections: " + size());
	}
	
	public int size(){
		return ALL_CLIENTS.size();
	}
	
}
