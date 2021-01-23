package com.gk.retailers.service.register;

import com.gk.retailers.entity.User;

public interface UserService {
	/**��ѯ�û����Ƿ�ռ��*/
	int findCountByNameService(String name);
	
	/**�û�ע��*/
	boolean addUserService(User user);
	
	/**�û���¼*/
	User findUserByPwdAndNameService(String name, String pwd);

	/**�����û�Id�޸��û�ͼƬ*/
	Boolean updateUserImageByIdService(Integer uId, String image);
	
	/**�����û�Id��ѯ�û�����*/
	User findUserByIdService(Integer id);
	
	/**�����û�id�޸��û��������*/
	Boolean updateUserByIdService(User user);
}
