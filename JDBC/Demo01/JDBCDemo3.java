package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * ����idɾ���û�����
 * @author LENOVO
 *
 */
public class JDBCDemo3 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			//1.�������ݿ�����
			Class.forName("com.mysql.jdbc.Driver");
			
			//2.�������ݿ��ַ
			String url = "jdbc:mysql://localhost:3306/store_ykt";
			
			String username = "root";
			
			String password = "984264";
			
			//3.��������
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("���ӳɹ�!");
			
			//4.ͨ��Connection���ӻ�ȡStatementʵ��
			Statement state = conn.createStatement();
			
			Scanner scanner = new Scanner(System.in);
			System.out.println("������ID:");
			String id = scanner.next();
			//����ɾ��SQL���
			String sql = "delete from myjdbc where id = " + id;
			
			int n = state.executeUpdate(sql);
			if(n > 0){
				System.out.println(n);
				System.out.println("ɾ���ɹ�!");
			}else{
				System.out.println(n);
				throw new RuntimeException("ɾ��ʧ��!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//�ͷ�����
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
