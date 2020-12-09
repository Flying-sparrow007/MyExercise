package com.gk.spring02;

import java.io.Serializable;

public class Student implements Serializable {
	
	private String userName;

	public Student() {
		super();
		System.out.println("Student的无参构造方法!");
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "Student [userName=" + userName + "]";
	}
	
}
