package com.gk.login.entity;

public class User {
	private String username;
	private String password;
	
	public User(){
		System.out.println("บวบว");
	}
	
	public String getUsername() {
		System.out.println("getU");
		return username;
	}
	
	public void setUsername(String username) {
		System.out.println("setU");
		this.username = username;
	}
	
	public String getPassword() {
		System.out.println("getP");
		return password;
	}
	
	public void setPassword(String password) {
		System.out.println("setP");
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
	
	
}
