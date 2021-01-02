package com.gk.login.service;

import com.gk.login.entity.User;

public interface UserService {
	/**校验用户登录是否成功*/
	User checkUserLogin(User user);
}
