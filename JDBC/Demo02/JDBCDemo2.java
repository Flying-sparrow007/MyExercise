package day02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 练习:根据员工编号查询领导所属的部门信息
 * @author LENOVO
 *
 */
public class JDBCDemo2 {
	public static void main(String[] args) {
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = DButil.getConnection();//建立连接
				
			Scanner scanner = new Scanner(System.in);
			System.out.println("请输入员工编号:");
			int empno = scanner.nextInt();
			
			Statement state = conn.createStatement();
			String sql = "select d.* from emp e left join emp p on e.mgr = p.empno left join dept d on d.deptno = p.deptno where e.empno = " + empno;
			rs = state.executeQuery(sql);
			while(rs.next()){
				int deptno = rs.getInt("deptno");
				String dname = rs.getString("dname");
				String loc = rs.getString("loc");
				System.out.println("部门编号:" + deptno + ",部门名称:" + dname + ",部门地址:" + loc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.closeConnection(conn, rs, null);
		}
		
	}
}
