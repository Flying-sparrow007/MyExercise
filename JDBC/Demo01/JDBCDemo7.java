package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

/**
 * 查询员工表所有信息和部门表所有信息根据员工表的工资降序排列
 * 每页显示3条数据,显示第4页的数据
 * @author LENOVO
 *
 */
public class JDBCDemo7 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("请输入页数:");
			int page = scanner.nextInt();
			System.out.println("请输入每一页显示的条数:");
			int pageSize = scanner.nextInt();
			//起始位置:(页数 - 1) * 每页显示的数量
			int start = (page - 1) * pageSize;
			//1.加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			
			//2.设置数据库信息
			String url = "jdbc:mysql://localhost:3306/store_ykt";
			String name = "root";
			String pwd = "984264";
			
			//3.建立连接
			conn = DriverManager.getConnection(url, name, pwd);
			
			//4.获取Statement实例
			Statement state = conn.createStatement();
			
			//5.SQL语句
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
