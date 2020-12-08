package com.gk.spring;
/**
 * ¸«Í·
 * @author LENOVO
 *
 */
public class Aex implements Tool {
	private String name;
	
	public Aex() {
		super();
		this.name = "¸«Í·";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Aex [name=" + name + "]";
	}

	@Override
	public String getToolName() {
		return "¸«Í·";
	}
	
}
