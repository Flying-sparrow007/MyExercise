package dao;

import java.util.List;

import entity.User;

public interface UserDao {
	
	/**����û�*/
	int addUser(User user);
	
	/**��ѯ�����û�����*/
	List<User> findAllUser();
	
	/**����IDɾ���û�*/
	int deleteUserById(int id);
	
	/**����ID�޸��û�����*/
	int updateUserPwdById(int id, String password);
	
	/**�����û����ֲ�������*/
	int findUserByName(String name);
	
	/**�����û�����ѯ�û�����*/
	String findUserPwdByName(String name);
}
