package com.gk.gkJavaSpring_ssm.login.service;

import com.gk.gkJavaSpring_ssm.login.entity.User;

public interface UserService {
	/**�û���¼*/
	User userLogin(String name, String pwd);
}
