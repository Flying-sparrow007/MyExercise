package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * �޸�����,ǰ�����û���½�ɹ�������û��������޸�����
 * @author LENOVO
 *
 */
public class JDBCDemo6 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			//1.�������ݿ�����
			Class.forName("com.mysql.jdbc.Driver");
			
			//2.���ݿ��ַ
			String url = "jdbc:mysql://localhost:3306/store_ykt";
			
			//���ݿ��˺�
			String name = "root";
			
			//���ݿ�����
			String password = "984264";
			
			//3.��������
			conn = DriverManager.getConnection(url, name, password);
			
			//4.����Connection��ȡִ��SQL���Ķ���Statement
			Statement state = conn.createStatement();
			
			Scanner scanner = new Scanner(System.in);
			System.out.println("�������û���:");
			String username = scanner.next();
			System.out.println("�������û�����:");
			String pwd = scanner.next();
			
			String sql = "select * from myjdbc where user_name = '" + username + "' and password = '" + pwd + "'";
			ResultSet rs = state.executeQuery(sql);
			if(rs.next()){
				System.out.println("��½�ɹ�!");
				System.out.println("�������޸ĺ������!");
				String newPwd = scanner.next();
				String sql2 = "update myjdbc set password = '" + newPwd + "' where user_name = '" + username + "'";
				int n = state.executeUpdate(sql2);
				if(n > 0){
					System.out.println("�޸ĳɹ�!");
				}else{
					throw new RuntimeException("�޸�ʧ��!");
				}
			}else{
				throw new RuntimeException("�˺Ż��������!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				//�ͷ�����
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
