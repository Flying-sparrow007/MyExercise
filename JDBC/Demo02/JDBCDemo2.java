package day02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 * ��ϰ:����Ա����Ų�ѯ�쵼�����Ĳ�����Ϣ
 * @author LENOVO
 *
 */
public class JDBCDemo2 {
	public static void main(String[] args) {
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = DButil.getConnection();//��������
				
			Scanner scanner = new Scanner(System.in);
			System.out.println("������Ա�����:");
			int empno = scanner.nextInt();
			
			Statement state = conn.createStatement();
			String sql = "select d.* from emp e left join emp p on e.mgr = p.empno left join dept d on d.deptno = p.deptno where e.empno = " + empno;
			rs = state.executeQuery(sql);
			while(rs.next()){
				int deptno = rs.getInt("deptno");
				String dname = rs.getString("dname");
				String loc = rs.getString("loc");
				System.out.println("���ű��:" + deptno + ",��������:" + dname + ",���ŵ�ַ:" + loc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.closeConnection(conn, rs, null);
		}
		
	}
}
