package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 给myjdbc表中插入相关数据
 * @author LENOVO
 *
 */
public class JDBCDemo2 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			//1.加载驱动包
			Class.forName("com.mysql.jdbc.Driver");
			
			//2.数据库地址
			String url = "jdbc:mysql://localhost:3306/store_ykt";
			
			//3.数据库账户
			String username = "root";
			
			//4.数据库密码
			String password = "984264";
			
			System.out.println("连接中...");
			//获取连接
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("连接成功!");
			
			//通过Connection连接获取执行SQL的实例Statement
			Statement state = conn.createStatement();
			/*
			 * 要求用户输入用户名,密码,性别插入到表myjdbc中
			 */
			Scanner scanner = new Scanner(System.in);
			System.out.println("请输入用户名:");
			String name = scanner.next();
			System.out.println("请输入用户密码:");
			String pwd = scanner.next();
			System.out.println("请输入用户性别:");
			char gender = scanner.next().charAt(0);
			
			String sql = "insert into myjdbc (user_name,password,gender) values ('" + name + "','" + pwd + "','" + gender + "')";
			
			//插入数据
			int n = state.executeUpdate(sql);
			if(n > 0){
				System.out.println("插入成功!");
			}else{
				throw new RuntimeException("插入失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				//释放资源
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}
}
