package com.gk.spring02;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Lazy(true)
@Scope("prototype")
public class User {
	private String name;

	public User() {
		System.out.println("User的无参构造器!");
	}

	public String getName() {
		return name;
	}

	@Value("admin")
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [name=" + name + "]";
	}
	
	
}
