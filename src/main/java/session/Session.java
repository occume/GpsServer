package session;

import io.netty.channel.Channel;

public class Session {
	
	private Channel channel;
	
	private long lastAccess;
	
	public Session(Channel channel){
		this.channel = channel;
	}
	
	
}
