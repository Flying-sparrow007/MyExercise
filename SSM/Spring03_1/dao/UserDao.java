package dao;

import java.util.List;

import entity.User;

public interface UserDao {
	
	/**添加用户*/
	int addUser(User user);
	
	/**查询所有用户数据*/
	List<User> findAllUser();
	
	/**根据ID删除用户*/
	int deleteUserById(int id);
	
	/**根据ID修改用户密码*/
	int updateUserPwdById(int id, String password);
	
	/**根据用户名字查找数量*/
	int findUserByName(String name);
	
	/**根据用户名查询用户密码*/
	String findUserPwdByName(String name);
}
