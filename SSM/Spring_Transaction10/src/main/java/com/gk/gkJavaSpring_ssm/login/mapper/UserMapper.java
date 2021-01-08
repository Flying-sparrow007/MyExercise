package com.gk.gkJavaSpring_ssm.login.mapper;

import org.apache.ibatis.annotations.Param;

import com.gk.gkJavaSpring_ssm.login.entity.User;

/**
 * Ó³ÉäÆ÷
 * @author LENOVO
 *
 */
public interface UserMapper {
	boolean addUser(User user);
	boolean deleteById(int id);
	
	boolean updateUserById(@Param("sal")double sal, @Param("id")int id);
	boolean updateUserById2(@Param("sal")double sal, @Param("id")int id);
}
