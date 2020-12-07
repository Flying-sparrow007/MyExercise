package dao;

import java.util.List;

import entity.User;

/**
 * 用户操作接口
 * @author LENOVO
 *
 */
public interface UserDao {
	/**用户注册*/
	int register(User user);
	
	/**根据用户名查询用户*/
	boolean selectUserByName(String username);
	
	/**查询用户列表*/
	List<User> selectUserList();
	
	/**根据用户账号和密码登录*/
	User loginByNameAndPwd(String name, String pwd);
	
	/**根据用户id删除用户*/
	int deleteUserById(int id);
	
	/**根据用户id查询用户信息*/
	User selectUserById(int id);
	
	/**根据用户id修改用户信息*/
	int updateUserMegById(User user);
	
	/**根据每一页的数据个数查询总页数*/
	int selectPageCountByUserCount();
	
	/**查询某一页的数据*/
	List<User> selectUserByPage(int page);
}
