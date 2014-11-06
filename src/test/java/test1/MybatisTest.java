package test1;


import manage.SpringConfig;
import mybatis.service.UserService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MybatisTest {
	
	public static void main(String...strings){
		
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
		UserService userService = (UserService) ctx.getBean("userService");
		String dept = userService.getDeptById(10);
		System.out.println(dept);
	}
	
}
