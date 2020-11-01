package day02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 课堂练习:
 * 根据部门编号查询该部门的平均工资,最低工资,最高工资,工资总和
 * @author LENOVO
 *
 */
public class JDBCDemo {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DButilDemo.getConnection();//建立连接
			Scanner scanner = new Scanner(System.in);
			System.out.println("请输入部门编号:");
			int deptno = scanner.nextInt();
			Statement state = conn.createStatement();//获取执行SQL语句的Statement实例
			String sql = "select avg(sal),min(sal),max(sal),sum(sal) from emp where deptno = " + deptno;
			ResultSet rs = state.executeQuery(sql);
			if(rs.next()){
				double avg = rs.getDouble("avg(sal)");
				double min = rs.getDouble("min(sal)");
				double max = rs.getDouble("max(sal)");
				double sum = rs.getDouble("sum(sal)");
				System.out.println(deptno + "号部门的平均工资:" + avg + ",最第工资:" + min + ",最高工资:" + max + ",工资总和:" + sum);
			}else{
				System.out.println("没有相关的部门");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButilDemo.closeConnection(conn);
		}
	}
}
