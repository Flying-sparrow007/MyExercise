package entity;

public class User2 {
	private int userId;
	private String userName;
	private String userPassword;
	private double userSal;
	private int userAge;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
		return "User2 [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", userSal="
				+ userSal + ", userAge=" + userAge + "]";
	}
}
