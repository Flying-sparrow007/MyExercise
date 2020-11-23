package test;

import java.util.List;

import org.junit.Test;

import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;

public class TestUser {
	
	//��������û�
	@Test
	public void testAddUser(){
		User user = new User();
		user.setUserName("����");
		user.setUserPwd("123456");
		user.setUserEmail("1373588696@qq.com");
		user.setUserPhone("123456789");
		UserDao dao = new UserDaoImpl();
		int n = dao.addUser(user);
		System.out.println(n);
	}
	
	//���Բ�ѯ�����û�
	@Test
	public void testSelectAllUser(){
		UserDao dao = new UserDaoImpl();
		List<User> list = dao.findAllUser();
		list.forEach(s -> {
			System.out.println(s);
		});
	}
	
	//���Ը���idɾ���û�
	@Test
	public void testDeleteUserById(){
		UserDao dao = new UserDaoImpl();
		dao.deleteUserById(2);
	}
	
	//���Ը���id�޸��û�����
	@Test
	public void testUpdateUserPwdById(){
		UserDao dao = new UserDaoImpl();
		int n = dao.updateUserPwdById(3, "456123");
		if(n > 0){
			System.out.println("�޸ĳɹ�!");
		}else{
			System.out.println("�޸�ʧ��!");
		}
	}
	
}
