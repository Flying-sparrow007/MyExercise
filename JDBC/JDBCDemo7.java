package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

/**
 * ��ѯԱ����������Ϣ�Ͳ��ű�������Ϣ����Ա����Ĺ��ʽ�������
 * ÿҳ��ʾ3������,��ʾ��4ҳ������
 * @author LENOVO
 *
 */
public class JDBCDemo7 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("������ҳ��:");
			int page = scanner.nextInt();
			System.out.println("������ÿһҳ��ʾ������:");
			int pageSize = scanner.nextInt();
			//��ʼλ��:(ҳ�� - 1) * ÿҳ��ʾ������
			int start = (page - 1) * pageSize;
			//1.�������ݿ�����
			Class.forName("com.mysql.jdbc.Driver");
			
			//2.�������ݿ���Ϣ
			String url = "jdbc:mysql://localhost:3306/store_ykt";
			String name = "root";
			String pwd = "984264";
			
			//3.��������
			conn = DriverManager.getConnection(url, name, pwd);
			
			//4.��ȡStatementʵ��
			Statement state = conn.createStatement();
			
			//5.SQL���
			String sql = "select e.*,d.* from emp e join dept d on e.deptno = d.deptno order by e.sal desc limit " + start + "," + pageSize;
			
			ResultSet rs = state.executeQuery(sql);
			
			while(rs.next()){
				int empno = rs.getInt("e.empno");
				String ename = rs.getString("e.ename");
				String job = rs.getString("e.job");
				int mgr = rs.getInt("e.mgr");
				Date hiredate = rs.getDate("e.hiredada");
				double comm = rs.getDouble("e.comm");
				int deptno = rs.getInt("d.deptno");
				String dname = rs.getString("d.dname");
				String loc = rs.getString("d.loc");
				System.out.println(empno + "," + ename + "," + job + "," + mgr + "," + hiredate + "," + comm + "," + deptno + "," + dname + "," + loc);
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
