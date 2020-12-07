package test;

import java.util.List;

import org.junit.Test;

import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;

/**
 * 用户测试
 * @author LENOVO
 * 
 */
public class UserTest {
	@Test
	public void testRegister(){
		UserDao dao = new UserDaoImpl();
		User user = new User();
		user.setUserName("张三");
		user.setPassword("123456");
		user.setUserEmail("123456789@163.com");
		user.setUserPhone("123456789");
		int n = dao.register(user);
		if(n > 0){
			System.out.println("注册成功!");
		}else{
			System.out.println("注册失败!");
		}
	}
	
	@Test
	public void testSelectUserByName(){
		UserDao dao = new UserDaoImpl();
		if(dao.selectUserByName("admin")){
			System.out.println("用户存在!");
		}else{
			System.out.println("用户不存在!");
		}
	}
	
	@Test
	public void testSelectUserList(){
		UserDao dao = new UserDaoImpl();
		List<User> list = dao.selectUserList();
		System.out.println(list);
	}
	
	@Test
	public void testLoginByNameAndPwd(){
		UserDao dao = new UserDaoImpl();
		User user = dao.loginByNameAndPwd("admin", "bc84c2112359d5f73e44fd9a6efd3c17");
		if(user != null){
			System.out.println("登录成功!");
		}else{
			System.out.println("登录失败!");
		}
	}
	
	@Test
	public void testDeleteUserById(){
		UserDao dao = new UserDaoImpl();
		System.out.println(dao.deleteUserById(9));
	}
	
	@Test
	public void testSelectUserById(){
		UserDao dao = new UserDaoImpl();
		System.out.println(dao.selectUserById(1));
	}
	
	@Test
	public void testUpdateUserMegById(){
		User user = new User();
		user.setId(1);
		user.setUserName("admin");
		user.setUserEmail("123457@qq.com");
		user.setUserPhone("18365955246");
		UserDao dao = new UserDaoImpl();
		int n = dao.updateUserMegById(user);
		System.out.println(n);
	}
	
	@Test
	public void testSelectUserByPage(){
		UserDao dao = new UserDaoImpl();
		System.out.println(dao.selectUserByPage(1));
	}
}
