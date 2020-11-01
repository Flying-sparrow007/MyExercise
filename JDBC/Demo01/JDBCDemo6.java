package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 修改密码,前提是用户登陆成功后根据用户名方可修改密码
 * @author LENOVO
 *
 */
public class JDBCDemo6 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			//1.加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			
			//2.数据库地址
			String url = "jdbc:mysql://localhost:3306/store_ykt";
			
			//数据库账号
			String name = "root";
			
			//数据库密码
			String password = "984264";
			
			//3.建立连接
			conn = DriverManager.getConnection(url, name, password);
			
			//4.根据Connection获取执行SQL语句的对象Statement
			Statement state = conn.createStatement();
			
			Scanner scanner = new Scanner(System.in);
			System.out.println("请输入用户名:");
			String username = scanner.next();
			System.out.println("请输入用户密码:");
			String pwd = scanner.next();
			
			String sql = "select * from myjdbc where user_name = '" + username + "' and password = '" + pwd + "'";
			ResultSet rs = state.executeQuery(sql);
			if(rs.next()){
				System.out.println("登陆成功!");
				System.out.println("请输入修改后的密码!");
				String newPwd = scanner.next();
				String sql2 = "update myjdbc set password = '" + newPwd + "' where user_name = '" + username + "'";
				int n = state.executeUpdate(sql2);
				if(n > 0){
					System.out.println("修改成功!");
				}else{
					throw new RuntimeException("修改失败!");
				}
			}else{
				throw new RuntimeException("账号或密码错误!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				//释放连接
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
