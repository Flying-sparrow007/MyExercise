package com.gk.spring01;

import java.io.Serializable;

public class Person implements Serializable {
	private String name;

	public Person() {
		System.out.println("Person���޲ι��췽��!");
	}

	public Person(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		System.out.println("name��Setע�뷽ʽ!");
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}
	
}
