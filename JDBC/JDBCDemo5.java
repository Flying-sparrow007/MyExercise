package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ��ѯ����:
 * ResultSet:��ʾ��ѯ�ý����,����������ɻ�ȡ��ѯ�ľ�������;
 * ResultSet executeQuery(String sql)(��ѯ)ר��ִ��DQL���,
 * ���ز�ѯ�Ľ����,��ResultSetʵ������
 * @author LENOVO
 *
 */
public class JDBCDemo5 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			//1.�������ݿ�����
			Class.forName("com.mysql.jdbc.Driver");
			
			//2.���ݿ��ַ
			String url = "jdbc:mysql://localhost:3306/store_ykt";
			
			String username = "root";
			
			String password = "984264";
			
			//3.��������
			conn = DriverManager.getConnection(url, username, password);
			
			//4.ͨ��Connection��ȡStatementʵ��
			Statement state = conn.createStatement();
			
			//5.дsql
			String sql = "select * from myjdbc";
			ResultSet rs = state.executeQuery(sql);
			/*
			 * ResultSet�ṩ��һ������next()������ݿ����һ����û������
			 * �������ݷ���true,���򷵻�false;
			 */
			while(rs.next()){
				//ͨ������е��±��ȡ����(���Ƽ�)
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String pwd = rs.getString(3);
				char gender = rs.getString(4).charAt(0);
				System.out.println(id + "," + name + "," + pwd + "," + gender);
				
				//ͨ�����������ȡ����
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
