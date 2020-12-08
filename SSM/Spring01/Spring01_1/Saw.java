package com.gk.spring;
/**
 * µç¾â
 * @author LENOVO
 *
 */
public class Saw implements Tool {
	private String name;

	public Saw() {
		super();
		this.name = "µç¾â";
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
		return "µç¾â";
	}
}
