package day02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 * ����Ա����Ų�ѯ�쵼�ĸ��˱�ź͹���
 * @author LENOVO
 *
 */
public class JDBCDemo1 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("������Ա�����:");
			int empno = scanner.nextInt();
			//��ȡ����
			conn = DButilDemo.getConnection();
			Statement state = conn.createStatement();
			String sql = "select empno,sal from emp where empno = (select mgr from emp where empno = " + empno + ")";
			ResultSet rs = state.executeQuery(sql);
			if(rs.next()){
				int en = rs.getInt("empno");
				double sal = rs.getDouble("sal");
				System.out.println("�쵼���:" + en + ",�쵼����:" + sal);
			}else{
				System.out.println("����ʧ��,û����ص��쵼!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButilDemo.closeConnection(conn);//�ͷ���Դ
		}
	}
}
