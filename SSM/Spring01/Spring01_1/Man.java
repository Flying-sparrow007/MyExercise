package com.gk.spring;
/**
 * ��ͷǿ
 * @author LENOVO
 *
 */
public class Man {
	private String manName;
	private Tool tool;
	
	public Man(){
		this.manName = "��ͷǿ";
	}
	
	public Tool getTool() {
		return tool;
	}

	public void setTool(Tool tool) {
		this.tool = tool;
	}

	public void work(){
		System.out.println(manName + "ʹ��" + tool.getToolName() + "������!");
	}
}
