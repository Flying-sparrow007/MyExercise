package com.gk.spring02;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * ��ͷǿ
 * @Value() Spring�ṩ��ע��,���Ը����Ը�ֵ,Ҫд��set�������������������
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
	
	@Value("��ͷǿ")
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
		System.out.println(manName + "ʹ��" + tool.getToolName() + "������!");
	}

	@Override
	public String toString() {
		return "Man [manName=" + manName + ", tool=" + tool + "]";
	}
	
}
