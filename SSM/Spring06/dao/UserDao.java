package com.gk.login.dao;

import com.gk.login.entity.User;

/**
 * �־ò�ӿ�
 * @author LENOVO
 *
 */
public interface UserDao {
	/**�����û�����ѯ�û�����*/
	User findUserByName(String name);
}
