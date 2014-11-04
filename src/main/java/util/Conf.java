package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.GpsServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Conf {
	
	private static final Logger LOG = LoggerFactory.getLogger(Conf.class);
	
	private static Resource res;
	
	private static Properties props = new Properties();
	
	static{
		props = new Properties();
		try {
			res = new ClassPathResource("/src/main/resource/conf.properties");
			System.out.println(res.getFilename());
			InputStream	ins = res.getInputStream();
			props.load(ins);
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
