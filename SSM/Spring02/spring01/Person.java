package com.gk.spring01;

import java.io.Serializable;

public class Person implements Serializable {
	private String name;

	public Person() {
		System.out.println("Person的无参构造方法!");
	}

	public Person(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		System.out.println("name的Set注入方式!");
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}
	
}
