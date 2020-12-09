package com.gk.spring02;
/**
 * 光头强
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
		System.out.println("Tool设置");
		this.tool = tool;
	}

	public void work(){
		System.out.println(manName + "使用" + tool.getToolName() + "来砍树!");
	}
}
