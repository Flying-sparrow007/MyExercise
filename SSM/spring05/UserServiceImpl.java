package com.gk.spring05;

import org.springframework.stereotype.Component;

/**
 * 真实角色
 * @author LENOVO
 *
 */
@Component
public class UserServiceImpl implements UserService {

	@Override
	public void add() {
		System.out.println("添加了一个用户");
	}

	@Override
	public void delete() {
		System.out.println("删除了一个用户");
	}

	@Override
	public void update() {
		System.out.println("修改了一个用户");
	}

	@Override
	public void select() {
		System.out.println("查询了一个用户");
	}

}
