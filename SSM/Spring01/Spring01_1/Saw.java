package com.gk.spring;
/**
 * ���
 * @author LENOVO
 *
 */
public class Saw implements Tool {
	private String name;

	public Saw() {
		super();
		this.name = "���";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Saw [name=" + name + "]";
	}

	@Override
	public String getToolName() {
		return "���";
	}
}
