package manage;

import java.beans.PropertyVetoException;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import util.Conf;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@ImportResource("classpath:beans.xml")
public class SpringConfig{
	
	private static final Logger LOG = LoggerFactory.getLogger(SpringConfig.class);
	
	public @Bean(name = "dataSource")
	DataSource anyRoom()
	{	

		String driver = Conf.getProperty("jdbc.driverClassName");
		String url = Conf.getProperty("jdbc.url");
		String name = Conf.getProperty("jdbc.username");
		String password = Conf.getProperty("jdbc.password");
		
		if(driver == null){
			driver = "org.gjt.mm.mysql.Driver";
			url = "jdbc:mysql://d3gameserver.mysql.rds.aliyuncs.com:3306/d3_game";
			name = "occume";
			password = "5651403";
//			driver = "oracle.jdbc.driver.OracleDriver";
//			url = "jdbc:oracle:thin:@localhost:1521:orcl";
//			name = "scott";
//			password = "5651403";
		}
		
		LOG.info("driver: " + driver);
		
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass(driver);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		dataSource.setJdbcUrl(url);
		dataSource.setUser(name);
		dataSource.setPassword(password);
		return dataSource;
	}
	
}
