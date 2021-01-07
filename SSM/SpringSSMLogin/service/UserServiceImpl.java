package com.gk.gkJavaSpring_ssm.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gk.gkJavaSpring_ssm.login.entity.User;
import com.gk.gkJavaSpring_ssm.login.mapper.UserMapper;

/**
 * 业务层
 * @author LENOVO
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	//注入持久层
	@Autowired
	private UserMapper mapper;
	
	/**用户登录*/
	@Override
	public User userLogin(String name, String pwd) {
		//根据用户名字查询该用户数据
		User user = mapper.findCountByName(name);
		if(user == null){
			throw new UserNameNotExistException("此用户不存在!");
		}
		//获取改名字再数据库中的密码
		String password = user.getUserPwd();
		if(!password.equals(pwd)){//判断用户输入的密码和数据库中的密码是否一样
			throw new UserPwdNotSameException("用户密码错误!");
		}
		//登录成功
		return user;
	}
	
}
