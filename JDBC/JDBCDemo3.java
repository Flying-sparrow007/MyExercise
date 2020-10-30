package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 根据id删除用户数据
 * @author LENOVO
 *
 */
public class JDBCDemo3 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			//1.加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			
			//2.设置数据库地址
			String url = "jdbc:mysql://localhost:3306/store_ykt";
			
			String username = "root";
			
			String password = "984264";
			
			//3.建立连接
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("连接成功!");
			
			//4.通过Connection连接获取Statement实例
			Statement state = conn.createStatement();
			
			Scanner scanner = new Scanner(System.in);
			System.out.println("请输入ID:");
			String id = scanner.next();
			//创建删除SQL语句
			String sql = "delete from myjdbc where id = " + id;
			
			int n = state.executeUpdate(sql);
			if(n > 0){
				System.out.println(n);
				System.out.println("删除成功!");
			}else{
				System.out.println(n);
				throw new RuntimeException("删除失败!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//释放连接
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}

}
