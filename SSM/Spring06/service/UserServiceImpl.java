package com.gk.login.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gk.login.dao.UserDao;
import com.gk.login.entity.User;

/**
 * 业务层实现类用@Service注解
 * @author LENOVO
 *
 */
@Service
public class UserServiceImpl implements UserService {
	//注入持久层
	@Resource
	private UserDao dao;
	
	@Override
	public User checkUserLogin(User user) {
		//获取用户名字
		String name = user.getUserName();
		User u = dao.findUserByName(name);
		if(u == null){
			throw new AppException("用户名不存在!");
		}
		//获取数据库中的用户密码
		String dbPwd = u.getUserPassword();
		//获取用户输入的密码
		String pwd = user.getUserPassword();
		if(!dbPwd.equals(pwd)){//判断数据库中提取出的密码和用户输入的密码是否一致
			throw new AppPwdException("用户密码不正确!");
		}
		
		//登录成功
		return u;
	}

}
