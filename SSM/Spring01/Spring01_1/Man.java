package com.gk.spring;
/**
 * 光头强
 * @author LENOVO
 *
 */
public class Man {
	private String manName;
	private Tool tool;
	
	public Man(){
		this.manName = "光头强";
	}
	
	public Tool getTool() {
		return tool;
	}

	public void setTool(Tool tool) {
		this.tool = tool;
	}

	public void work(){
		System.out.println(manName + "使用" + tool.getToolName() + "来砍树!");
	}
}
