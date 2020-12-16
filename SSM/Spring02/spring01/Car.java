package com.gk.spring01;

import java.io.Serializable;

/**
 * Æû³µ
 * @author LENOVO
 *
 */
public class Car implements Serializable {
	private String name;

	public Car() {
		super();
		System.out.println("CarµÄÊµÀý!");
	}

	public Car(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Car [name=" + name + "]";
	}
	
}
