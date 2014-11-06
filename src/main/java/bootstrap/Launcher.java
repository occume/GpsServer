package bootstrap;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import manage.SpringConfig;
import net.GpsServer;

public class Launcher {

	public static void main(String[] args) {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
		
		GpsServer server = (GpsServer) context.getBean("gpsServer");
		server.start();
		
	}

}
