package com.gk.spring;
/**
 * นท
 * @author LENOVO
 *
 */
public class Dog implements Anm {
	private String name;

	public Dog() {
		super();
		this.name = "นท";
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
		return "อ๔อ๔อ๔";
	}
	
}
