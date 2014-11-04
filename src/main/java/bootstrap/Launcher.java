package bootstrap;

import net.GpsServer;

public class Launcher {
	
	private static GpsServer server = new GpsServer();

	public static void main(String[] args) {
		
		if(args.length == 0){
			System.out.println("Usage: start) stop)");
			return;
		}
		
		String cmd = args[0];
		
		if("start".equals(cmd)){
			server.start();
		}
		else if("stop".equals(cmd)){
			server.stop();
		}
		
	}

}
