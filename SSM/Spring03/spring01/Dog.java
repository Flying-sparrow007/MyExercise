package com.gk.spring01;

import org.springframework.stereotype.Component;

/**
 * ��
 * @author LENOVO
 *
 */
@Component
public class Dog implements Anm {
	private String name;

	public Dog() {
		super();
		this.name = "��";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Dog [name=" + name + "]";
	}

	@Override
	public String yell() {
		return "������";
	}
	
}
