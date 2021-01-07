package com.gk.gkJavaSpring_ssm.login.mapper;

import com.gk.gkJavaSpring_ssm.login.entity.User;

/**
 * 映射器
 * @author LENOVO
 *
 */
public interface UserMapper {
	/**根据用户的名字查询数量*/
	User findCountByName(String name);
}
