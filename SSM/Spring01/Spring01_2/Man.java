package com.gk.spring02;
/**
 * ��ͷǿ
 * @author LENOVO
 *
 */
public class Man {
	private String manName;
	private Tool tool;
	
	public Man(){
		
	}
	
	public String getManName() {
		return manName;
	}

	public void setManName(String manName) {
		this.manName = manName;
	}

	public Tool getTool() {
		return tool;
	}

	public void setTool(Tool tool) {
		System.out.println("Tool����");
		this.tool = tool;
	}

	public void work(){
		System.out.println(manName + "ʹ��" + tool.getToolName() + "������!");
	}
}
