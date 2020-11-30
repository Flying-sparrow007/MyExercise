package test;

import org.junit.Test;

import dao.UserDao;
import dao.UserDaoImpl;

public class TestUser {
	@Test
	public void testFindUserByPage(){
		UserDao dao = new UserDaoImpl();
		System.out.println(dao.findUserByPage(3, 2));
	}
	
	@Test
	public void testSelectUserCountAll(){
		UserDao dao = new UserDaoImpl();
		System.out.println(dao.selectUserCountAll());
	}
}
