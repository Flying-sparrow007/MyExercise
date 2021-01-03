package test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.UserDao;
import entity.User;

public class TestJDBC {
	ApplicationContext ctx;
	UserDao dao;
	
	@Before
	public void init(){
		ctx = new ClassPathXmlApplicationContext("spring-jdbc.xml");
		dao = ctx.getBean("userDao", UserDao.class);
	}
	
	@Test
	public void test01() throws SQLException{
		DataSource d = ctx.getBean("dataSource", DataSource.class);
		Connection conn = d.getConnection();
		System.out.println(conn);
	}
	
	@Test
	public void test02(){
		User user = new User();
		user.setUserAge(10);
		user.setUserName("บวบว");
		user.setUserPassword("666666");
		user.setUserSal(10000.00);
		UserDao dao = ctx.getBean("userDao", UserDao.class);
		dao.saveUser(user);
	}
	
	@Test
	public void test03(){
		List<User> list = dao.findUserAll();
		System.out.println(list);
	}
	
	@Test
	public void test04(){
		System.out.println(dao.findUserById(7));
	}
	
	@Test
	public void test05(){
		dao.deleteUserById(7);
	}
	
	@Test
	public void test06(){
		dao.updateUserById("654321", 9);
	}
}
