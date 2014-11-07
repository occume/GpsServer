package manage;

import java.util.List;

import javax.annotation.PostConstruct;

import mybatis.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

@Component
public class World implements ApplicationContextAware{
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		World.applicationContext = applicationContext;
	}

	private static ApplicationContext applicationContext;
	
	public static Object getBean(String beanName){
		if (null == beanName){
			return null;
		}
		return applicationContext.getBean(beanName);
	}
	
	@PostConstruct
	public void onStart() {
		List<String> tracks = userService.getAllTrackTables();
		LOG.info(tracks.toString());
		Sharding.instance().initTables(tracks);
	}
	
	private static World world = new World();
	
	@Autowired
	private UserService userService;
	
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
		LOG.info("A new connection disconnected, total connections: " + size());
	}
	
	public int size(){
		return ALL_CLIENTS.size();
	}
	
}
