package com.gk.gkJavaSpring_ssm.login.mapper;

import com.gk.gkJavaSpring_ssm.login.entity.User;

/**
 * ӳ����
 * @author LENOVO
 *
 */
public interface UserMapper {
	/**�����û������ֲ�ѯ����*/
	User findCountByName(String name);
}
