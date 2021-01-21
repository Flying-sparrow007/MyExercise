package com.gk.retailers.mapper.register;

import org.apache.ibatis.annotations.Param;

import com.gk.retailers.entity.User;

/**
 * 映射器
 * @author LENOVO
 *
 */
public interface UserMapper {
	/**查询用户名是否被占用*/
	int findCountByNameMapper(String name);
	
	/**用户注册*/
	boolean addUserMapper(User user);
	
	/**用户登录*/
	User findUserByPwdAndNameMapper(@Param("name")String name, @Param("pwd")String pwd);

	/**根据用户Id修改用户图片*/
	Boolean updateUserImageByIdMapper(@Param("uId")Integer uId, @Param("image")String image);
	
	/**根据用户Id查询用户数据*/
	User findUserByIdMapper(Integer id);
	
	/**根据用户id修改用户相关数据*/
	Boolean updateUserByIdMapper(User user);
}
