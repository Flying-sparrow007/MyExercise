package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * �����û�ID�޸��û�����
 * @author LENOVO
 *
 */
public class JDBCDemo4 {
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
			
			Scanner scanner = new Scanner(System.in);
			System.out.println("��������Ҫ�޸ĵ�ID:");
			int id = scanner.nextInt();
			System.out.println("�������޸ĺ������:");
			String pwd = scanner.next();
			
			String sql = "update myjdbc set password = " + pwd + " where id = " + id;
			
			int n = state.executeUpdate(sql);
			
			if(n > 0){
				System.out.println(n);
				System.out.println("�޸ĳɹ�!");
			}else{
				System.out.println(n);
				throw new RuntimeException("�޸�ʧ��!");
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
