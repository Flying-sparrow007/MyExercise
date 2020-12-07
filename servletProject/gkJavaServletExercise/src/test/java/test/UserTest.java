package test;

import java.util.List;

import org.junit.Test;

import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;

/**
 * �û�����
 * @author LENOVO
 * 
 */
public class UserTest {
	@Test
	public void testRegister(){
		UserDao dao = new UserDaoImpl();
		User user = new User();
		user.setUserName("����");
		user.setPassword("123456");
		user.setUserEmail("123456789@163.com");
		user.setUserPhone("123456789");
		int n = dao.register(user);
		if(n > 0){
			System.out.println("ע��ɹ�!");
		}else{
			System.out.println("ע��ʧ��!");
		}
	}
	
	@Test
	public void testSelectUserByName(){
		UserDao dao = new UserDaoImpl();
		if(dao.selectUserByName("admin")){
			System.out.println("�û�����!");
		}else{
			System.out.println("�û�������!");
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
			System.out.println("��¼�ɹ�!");
		}else{
			System.out.println("��¼ʧ��!");
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
