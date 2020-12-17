package com.gk.spring02;

public class TestBean {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "TestBean [message=" + message + "]";
	}
	
}
