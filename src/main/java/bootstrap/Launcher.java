package bootstrap;

import net.GpsServer;

public class Launcher {

	public static void main(String[] args) {
		
		GpsServer server = new GpsServer();
		server.start();
		
	}

}
