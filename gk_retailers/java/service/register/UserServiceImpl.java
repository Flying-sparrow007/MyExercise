package com.gk.retailers.service.register;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gk.retailers.commons.HandlerException;
import com.gk.retailers.entity.User;
import com.gk.retailers.mapper.register.UserMapper;

/**
 * ҵ���
 * @author LENOVO
 *
 */
@Service
public class UserServiceImpl implements UserService {
	//���ó־ò�
	@Autowired
	private UserMapper mapper;

	@Override
	public int findCountByNameService(String name) {
		//���������null��������Ϊ��ʱ
		if(name == null || name.trim().isEmpty()){
			throw new RuntimeException("�û�������Ϊ��!");
		}
		
		if(name.contains(" ")){
			throw new RuntimeException("�û������ܰ����ո�");
		}
		
		int count = mapper.findCountByNameMapper(name);
		
		if(count > 0){//��count����0��֤���û�����ռ��
			throw new RuntimeException("�û����Ѵ���!");
		}
		return count;
	}

	/**�û�ע��*/
	@Override
	public boolean addUserService(User user) {
		//��ȡ�û��ύ���û���
		String name = user.getUserName();
		//�жϸ��û��Ƿ�ռ��
		int count = mapper.findCountByNameMapper(name);
		
		if(count > 0){//��count����0��֤���û�����ռ��
			throw new RuntimeException("û������,�û�����ռ����!");
		}
		
		///��
		String salt = "�ɰ�������";
		
		//��ȡ����ǰ������
		String pwd = user.getPassword();
		
		//����
		String pwdMd5 = DigestUtils.md5Hex(pwd + salt);
		
		//�����ܹ��������������ݿ�
		user.setPassword(pwdMd5);
		
		boolean b = mapper.addUserMapper(user);
		
		return b;
	}

	//�û���¼
	@Override
	public User findUserByPwdAndNameService(String name, String pwd) {
		//��
		String salt = "�ɰ�������";
		
		//����
		String pwdMd5 = DigestUtils.md5Hex(pwd + salt);
		
		User user = mapper.findUserByPwdAndNameMapper(name, pwdMd5);
		if(user == null){
			throw new RuntimeException("�û������������!");
		}
		return user;
	}

	/**�����û�Id�޸��û�ͼƬ*/
	@Override
	public Boolean updateUserImageByIdService(Integer uId, String image) {
		return mapper.updateUserImageByIdMapper(uId, image);
	}

	/**�����û�Id��ѯ�û�����*/
	@Override
	public User findUserByIdService(Integer id) {
		return mapper.findUserByIdMapper(id);
	}

	@Override
	public Boolean updateUserByIdService(User user) {
		if(user.getId() == null){
			throw new RuntimeException("�û�idΪ��");
		}
		return mapper.updateUserByIdMapper(user);
	}

}
