package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 查询数据:
 * ResultSet:表示查询得结果集,遍历结果集可获取查询的具体数据;
 * ResultSet executeQuery(String sql)(查询)专门执行DQL语句,
 * 返回查询的结果集,用ResultSet实例返回
 * @author LENOVO
 *
 */
public class JDBCDemo5 {
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
			
			//5.写sql
			String sql = "select * from myjdbc";
			ResultSet rs = state.executeQuery(sql);
			/*
			 * ResultSet提供了一个方法next()检测数据库表下一行有没有数据
			 * 若有数据返回true,否则返回false;
			 */
			while(rs.next()){
				//通过表的列的下标获取数据(不推荐)
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String pwd = rs.getString(3);
				char gender = rs.getString(4).charAt(0);
				System.out.println(id + "," + name + "," + pwd + "," + gender);
				
				//通过表的列名获取数据
				/*int id = rs.getInt("id");
				String name = rs.getString("user_name");
				String pwd = rs.getString("password");
				String gender = rs.getString("gender");
				System.out.println(id + "," + name + "," + pwd + "," + gender);*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
