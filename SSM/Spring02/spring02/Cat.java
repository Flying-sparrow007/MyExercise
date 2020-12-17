package com.gk.spring02;
/**
 * Γ¨
 * @author LENOVO
 *
 */
public class Cat implements Anm {
	private String name;

	public Cat() {
		super();
		name = "Γ¨";
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
		return "ίχίχίχ";
	}
	
}
