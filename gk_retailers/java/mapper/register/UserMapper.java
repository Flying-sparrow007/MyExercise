package com.gk.retailers.mapper.register;

import org.apache.ibatis.annotations.Param;

import com.gk.retailers.entity.User;

/**
 * ӳ����
 * @author LENOVO
 *
 */
public interface UserMapper {
	/**��ѯ�û����Ƿ�ռ��*/
	int findCountByNameMapper(String name);
	
	/**�û�ע��*/
	boolean addUserMapper(User user);
	
	/**�û���¼*/
	User findUserByPwdAndNameMapper(@Param("name")String name, @Param("pwd")String pwd);

	/**�����û�Id�޸��û�ͼƬ*/
	Boolean updateUserImageByIdMapper(@Param("uId")Integer uId, @Param("image")String image);
	
	/**�����û�Id��ѯ�û�����*/
	User findUserByIdMapper(Integer id);
	
	/**�����û�id�޸��û��������*/
	Boolean updateUserByIdMapper(User user);
}
