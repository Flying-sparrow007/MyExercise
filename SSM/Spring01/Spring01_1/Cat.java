package com.gk.spring;
/**
 * è
 * @author LENOVO
 *
 */
public class Cat implements Anm {
	private String name;

	public Cat() {
		super();
		name = "è";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Cat [name=" + name + "]";
	}

	@Override
	public String yell() {
		return "������";
	}
	
}
