package com.gk.spring02;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * 光头强
 * @Value() Spring提供的注解,可以给属性赋值,要写在set方法上面或者属性上面
 * @author LENOVO
 *
 */
public class Man implements Serializable {
	private String manName;
	@Autowired
	private Tool tool;
	
	public String getManName() {
		return manName;
	}
	
	@Value("光头强")
	public void setManName(String manName) {
		this.manName = manName;
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

	@Override
	public String toString() {
		return "Man [manName=" + manName + ", tool=" + tool + "]";
	}
	
}
