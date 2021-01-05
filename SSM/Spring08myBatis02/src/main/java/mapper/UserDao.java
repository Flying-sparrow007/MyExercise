package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.User;

/**
 * 映射器(Mapper):符合映射文件要求的接口
 * 1.方法名必须与sql的唯一id一样
 * 2.参数类型必须与parameterType一样
 * 3.返回类型必须与resultType一样
 * 4.映射文件中的namespace必须与Mapper映射器全名(包名.类型)一样
 * 该接口中的方法名对应的是映射文件中的唯一的那个id
 * @author LENOVO
 *
 */
public interface UserDao {
	/**查询所有用户数据*/
	List<User> findAllUser();
	
	/**根据用户id查询用户数据*/
	User findUserById(int id);
	
	/**添加用户*/
	int addUser(User user);
	
	/**根据用户id删除该用户*/
	int deleteUserById(int id);
	
	/**根据用户ID修改用户账号和密码*/
	int updateUserById(User user);
	
	/**
	 * 当多个参数时需要添加@Param注解,写入形参对应映射文件的获取参数
	 * @param password
	 * @param id
	 * @return
	 */
	int updateUserById2(@Param("pwd")String password, @Param("userId")int id);
}
