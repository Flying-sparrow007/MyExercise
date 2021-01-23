package com.gk.retailers.service.register;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gk.retailers.commons.HandlerException;
import com.gk.retailers.entity.User;
import com.gk.retailers.mapper.register.UserMapper;

/**
 * 业务层
 * @author LENOVO
 *
 */
@Service
public class UserServiceImpl implements UserService {
	//调用持久层
	@Autowired
	private UserMapper mapper;

	@Override
	public int findCountByNameService(String name) {
		//如果名字是null或者名字为空时
		if(name == null || name.trim().isEmpty()){
			throw new RuntimeException("用户名不能为空!");
		}
		
		if(name.contains(" ")){
			throw new RuntimeException("用户名不能包含空格");
		}
		
		int count = mapper.findCountByNameMapper(name);
		
		if(count > 0){//若count大于0则证明用户名被占用
			throw new RuntimeException("用户名已存在!");
		}
		return count;
	}

	/**用户注册*/
	@Override
	public boolean addUserService(User user) {
		//获取用户提交的用户名
		String name = user.getUserName();
		//判断该用户是否被占用
		int count = mapper.findCountByNameMapper(name);
		
		if(count > 0){//若count大于0则证明用户名被占用
			throw new RuntimeException("没看懂吧,用户名被占用了!");
		}
		
		///盐
		String salt = "干拌拉条子";
		
		//获取加密前的密码
		String pwd = user.getPassword();
		
		//加密
		String pwdMd5 = DigestUtils.md5Hex(pwd + salt);
		
		//将加密过后的密码放入数据库
		user.setPassword(pwdMd5);
		
		boolean b = mapper.addUserMapper(user);
		
		return b;
	}

	//用户登录
	@Override
	public User findUserByPwdAndNameService(String name, String pwd) {
		//盐
		String salt = "干拌拉条子";
		
		//加密
		String pwdMd5 = DigestUtils.md5Hex(pwd + salt);
		
		User user = mapper.findUserByPwdAndNameMapper(name, pwdMd5);
		if(user == null){
			throw new RuntimeException("用户名或密码错误!");
		}
		return user;
	}

	/**根据用户Id修改用户图片*/
	@Override
	public Boolean updateUserImageByIdService(Integer uId, String image) {
		return mapper.updateUserImageByIdMapper(uId, image);
	}

	/**根据用户Id查询用户数据*/
	@Override
	public User findUserByIdService(Integer id) {
		return mapper.findUserByIdMapper(id);
	}

	@Override
	public Boolean updateUserByIdService(User user) {
		if(user.getId() == null){
			throw new RuntimeException("用户id为空");
		}
		return mapper.updateUserByIdMapper(user);
	}

}
