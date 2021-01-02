package com.gk.login.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gk.login.dao.UserDao;
import com.gk.login.entity.User;

/**
 * ҵ���ʵ������@Serviceע��
 * @author LENOVO
 *
 */
@Service
public class UserServiceImpl implements UserService {
	//ע��־ò�
	@Resource
	private UserDao dao;
	
	@Override
	public User checkUserLogin(User user) {
		//��ȡ�û�����
		String name = user.getUserName();
		User u = dao.findUserByName(name);
		if(u == null){
			throw new AppException("�û���������!");
		}
		//��ȡ���ݿ��е��û�����
		String dbPwd = u.getUserPassword();
		//��ȡ�û����������
		String pwd = user.getUserPassword();
		if(!dbPwd.equals(pwd)){//�ж����ݿ�����ȡ����������û�����������Ƿ�һ��
			throw new AppPwdException("�û����벻��ȷ!");
		}
		
		//��¼�ɹ�
		return u;
	}

}
