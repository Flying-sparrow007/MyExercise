package com.gk.login.dao;

import com.gk.login.entity.User;

/**
 * 持久层接口
 * @author LENOVO
 *
 */
public interface UserDao {
	/**根据用户名查询用户数据*/
	User findUserByName(String name);
}
