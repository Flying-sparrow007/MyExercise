package com.gk.login.entity;

public class User {
	private int id;
	private String userName;
	private String userPassword;
	private double userSal;
	private int userAge;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id, String userName, String userPassword, double userSal, int userAge) {
		super();
		this.id = id;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userSal = userSal;
		this.userAge = userAge;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public double getUserSal() {
		return userSal;
	}

	public void setUserSal(double userSal) {
		this.userSal = userSal;
	}

	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", userPassword=" + userPassword + ", userSal=" + userSal
				+ ", userAge=" + userAge + "]";
	}
	
	
}
