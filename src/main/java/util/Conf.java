package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import net.GpsServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Conf {
	
	private static final Logger LOG = LoggerFactory.getLogger(Conf.class);
	
	private static Properties props = new Properties();
	
	static{
		props = new Properties();
		try {
			props.load(new FileInputStream(new File("conf.properties")));
			System.out.println(props);
		} catch (FileNotFoundException e) {
			LOG.error("配置文件不存在");
		} catch (IOException e) {
			LOG.error("配置文件读取异常");
		}
	}
	
	public static String getProperty(String key){
		return props.getProperty(key);
	}
	
	public static void main(String...strings){
		System.out.println(Conf.getProperty(Constants.ConfKey.SERVER_PORT));
	}
}
