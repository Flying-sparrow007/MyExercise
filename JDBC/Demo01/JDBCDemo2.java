package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * ��myjdbc���в����������
 * @author LENOVO
 *
 */
public class JDBCDemo2 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			//1.����������
			Class.forName("com.mysql.jdbc.Driver");
			
			//2.���ݿ��ַ
			String url = "jdbc:mysql://localhost:3306/store_ykt";
			
			//3.���ݿ��˻�
			String username = "root";
			
			//4.���ݿ�����
			String password = "984264";
			
			System.out.println("������...");
			//��ȡ����
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("���ӳɹ�!");
			
			//ͨ��Connection���ӻ�ȡִ��SQL��ʵ��Statement
			Statement state = conn.createStatement();
			/*
			 * Ҫ���û������û���,����,�Ա���뵽��myjdbc��
			 */
			Scanner scanner = new Scanner(System.in);
			System.out.println("�������û���:");
			String name = scanner.next();
			System.out.println("�������û�����:");
			String pwd = scanner.next();
			System.out.println("�������û��Ա�:");
			char gender = scanner.next().charAt(0);
			
			String sql = "insert into myjdbc (user_name,password,gender) values ('" + name + "','" + pwd + "','" + gender + "')";
			
			//��������
			int n = state.executeUpdate(sql);
			if(n > 0){
				System.out.println("����ɹ�!");
			}else{
				throw new RuntimeException("����ʧ��!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				//�ͷ���Դ
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}
}
