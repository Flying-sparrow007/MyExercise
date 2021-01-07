package com.gk.gkJavaSpring_ssm.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gk.gkJavaSpring_ssm.login.entity.User;
import com.gk.gkJavaSpring_ssm.login.mapper.UserMapper;

/**
 * ҵ���
 * @author LENOVO
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	//ע��־ò�
	@Autowired
	private UserMapper mapper;
	
	/**�û���¼*/
	@Override
	public User userLogin(String name, String pwd) {
		//�����û����ֲ�ѯ���û�����
		User user = mapper.findCountByName(name);
		if(user == null){
			throw new UserNameNotExistException("���û�������!");
		}
		//��ȡ�����������ݿ��е�����
		String password = user.getUserPwd();
		if(!password.equals(pwd)){//�ж��û��������������ݿ��е������Ƿ�һ��
			throw new UserPwdNotSameException("�û��������!");
		}
		//��¼�ɹ�
		return user;
	}
	
}
