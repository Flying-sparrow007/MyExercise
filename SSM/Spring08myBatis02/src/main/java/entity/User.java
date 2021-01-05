package entity;

public class User {
	private int id;
	private String user_name;
	private String user_password;
	private double sal;
	private int user_age;
	
	public User() {
		super();
	}

	public User(int id, String user_name, String user_password, double sal, int user_age) {
		super();
		this.id = id;
		this.user_name = user_name;
		this.user_password = user_password;
		this.sal = sal;
		this.user_age = user_age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public double getSal() {
		return sal;
	}

	public void setSal(double sal) {
		this.sal = sal;
	}

	public int getUser_age() {
		return user_age;
	}

	public void setUser_age(int user_age) {
		this.user_age = user_age;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", user_name=" + user_name + ", user_password=" + user_password + ", sal=" + sal
				+ ", user_age=" + user_age + "]";
	}
	
}
