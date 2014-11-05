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
	
	private static String confName = "/conf/conf.properties";
	
	static{
		props = new Properties();
		try {
			
			res = new ClassPathResource("/");
			String parentPath = res.getFile().getParentFile().getPath();
			String confPath = parentPath + confName;
			InputStream	ins = new FileInputStream(new File(confPath));
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
	
	public static int getInt(String key){
		int ret = 0;
		try{
			ret = Integer.valueOf(props.getProperty(key));
		}catch(Exception e){
			LOG.error("读取配置配置异常：" + key);
		}
		return ret;
	}
	
	public static void main(String...strings){
		System.out.println(Conf.getProperty(Constants.ConfKey.SERVER_PORT));
	}
}
