package com.gk.spring05;

import org.springframework.stereotype.Component;

/**
 * ��ʵ��ɫ
 * @author LENOVO
 *
 */
@Component
public class UserServiceImpl implements UserService {

	@Override
	public void add() {
		System.out.println("�����һ���û�");
	}

	@Override
	public void delete() {
		System.out.println("ɾ����һ���û�");
	}

	@Override
	public void update() {
		System.out.println("�޸���һ���û�");
	}

	@Override
	public void select() {
		System.out.println("��ѯ��һ���û�");
	}

}
