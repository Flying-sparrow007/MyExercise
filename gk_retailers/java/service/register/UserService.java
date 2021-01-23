package com.gk.retailers.service.register;

import com.gk.retailers.entity.User;

public interface UserService {
	/**查询用户名是否被占用*/
	int findCountByNameService(String name);
	
	/**用户注册*/
	boolean addUserService(User user);
	
	/**用户登录*/
	User findUserByPwdAndNameService(String name, String pwd);

	/**根据用户Id修改用户图片*/
	Boolean updateUserImageByIdService(Integer uId, String image);
	
	/**根据用户Id查询用户数据*/
	User findUserByIdService(Integer id);
	
	/**根据用户id修改用户相关数据*/
	Boolean updateUserByIdService(User user);
}
