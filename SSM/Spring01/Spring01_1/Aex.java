package com.gk.spring;
/**
 * ��ͷ
 * @author LENOVO
 *
 */
public class Aex implements Tool {
	private String name;
	
	public Aex() {
		super();
		this.name = "��ͷ";
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
		return "��ͷ";
	}
	
}
