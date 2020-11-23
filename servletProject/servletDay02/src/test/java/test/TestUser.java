package test;

import java.util.List;

import org.junit.Test;

import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;

public class TestUser {
	
	//测试添加用户
	@Test
	public void testAddUser(){
		User user = new User();
		user.setUserName("高轲");
		user.setUserPwd("123456");
		user.setUserEmail("1373588696@qq.com");
		user.setUserPhone("123456789");
		UserDao dao = new UserDaoImpl();
		int n = dao.addUser(user);
		System.out.println(n);
	}
	
	//测试查询所有用户
	@Test
	public void testSelectAllUser(){
		UserDao dao = new UserDaoImpl();
		List<User> list = dao.findAllUser();
		list.forEach(s -> {
			System.out.println(s);
		});
	}
	
	//测试根据id删除用户
	@Test
	public void testDeleteUserById(){
		UserDao dao = new UserDaoImpl();
		dao.deleteUserById(2);
	}
	
	//测试根据id修改用户密码
	@Test
	public void testUpdateUserPwdById(){
		UserDao dao = new UserDaoImpl();
		int n = dao.updateUserPwdById(3, "456123");
		if(n > 0){
			System.out.println("修改成功!");
		}else{
			System.out.println("修改失败!");
		}
	}
	
}
