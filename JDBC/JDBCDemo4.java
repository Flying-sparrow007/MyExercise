package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 根据用户ID修改用户密码
 * @author LENOVO
 *
 */
public class JDBCDemo4 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			//1.加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			
			//2.数据库地址
			String url = "jdbc:mysql://localhost:3306/store_ykt";
			
			String username = "root";
			
			String password = "984264";
			
			//3.建立连接
			conn = DriverManager.getConnection(url, username, password);
			
			//4.通过Connection获取Statement实例
			Statement state = conn.createStatement();
			
			Scanner scanner = new Scanner(System.in);
			System.out.println("请输入所要修改的ID:");
			int id = scanner.nextInt();
			System.out.println("请输入修改后的密码:");
			String pwd = scanner.next();
			
			String sql = "update myjdbc set password = " + pwd + " where id = " + id;
			
			int n = state.executeUpdate(sql);
			
			if(n > 0){
				System.out.println(n);
				System.out.println("修改成功!");
			}else{
				System.out.println(n);
				throw new RuntimeException("修改失败!");
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
