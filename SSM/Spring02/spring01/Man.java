package com.gk.spring01;

import java.io.Serializable;

public class Man implements Serializable {
	private String name;
	private int age;
	
	public Man() {
		super();
		System.out.println("Man的无参构造方法!");
	}

	public Man(String name, int age) {
		super();
		System.out.println("Man的有参构造方法!");
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Man [name=" + name + ", age=" + age + "]";
	}
	
}
