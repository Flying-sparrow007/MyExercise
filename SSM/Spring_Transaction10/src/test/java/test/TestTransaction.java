package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gk.gkJavaSpring_ssm.login.entity.User;
import com.gk.gkJavaSpring_ssm.login.mapper.UserMapper;
import com.gk.gkJavaSpring_ssm.login.service.UserServiceImpl;

public class TestTransaction {
	ApplicationContext ctx;
	UserServiceImpl service;
	UserMapper mapper;
	
	@Test
	@Before
	public void init(){
		ctx = new ClassPathXmlApplicationContext("spring-mvc.xml", "spring-dao.xml");
		service = ctx.getBean("userService", UserServiceImpl.class);
		mapper = ctx.getBean("userMapper", UserMapper.class);
	}
	
	@Test
	public void testAddUser(){
		User user = new User();
		user.setUserName("admin");
		user.setUserPwd("123456");
		user.setUserSal(15622.25);
		user.setUserAge(23);
		mapper.addUser(user);
	}
	
	@Test
	public void testDeleteById(){
		System.out.println(mapper.deleteById(14));
	}
	
	@Test
	public void testTransactionDemo(){
		User user = new User();
		user.setUserName("admin2");
		user.setUserPwd("123456");
		user.setUserSal(1256.00);
		user.setUserAge(21);
		service.transactionDemo(user, 17);
	}
	
	@Test
	public void testTransactionDemo2(){
		service.transactionDemo2(1000, 15, 17);
	}
	
}
