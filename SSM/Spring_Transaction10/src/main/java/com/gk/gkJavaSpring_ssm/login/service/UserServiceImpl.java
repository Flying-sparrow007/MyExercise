package com.gk.gkJavaSpring_ssm.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gk.gkJavaSpring_ssm.login.entity.User;
import com.gk.gkJavaSpring_ssm.login.mapper.UserMapper;

/**
 * 业务层
 * @author LENOVO
 *
 */
@Service("userService")
public class UserServiceImpl {
	//注入持久层
	@Autowired
	private UserMapper mapper;
	
	@Transactional
	public void transactionDemo(User user, int id){
		mapper.addUser(user);
		mapper.deleteById(id);
	}
	
	@Transactional
	public void transactionDemo2(double sal, int id1, int id2){
		mapper.updateUserById(sal, id1);
		boolean flag = true;
		if(flag){
			throw new RuntimeException("错了");
		}
		mapper.updateUserById2(sal, id2);
	}
	
}
