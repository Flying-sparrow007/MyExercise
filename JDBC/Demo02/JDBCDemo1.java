package day02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 根据员工编号查询领导的个人编号和工资
 * @author LENOVO
 *
 */
public class JDBCDemo1 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("请输入员工编号:");
			int empno = scanner.nextInt();
			//获取连接
			conn = DButilDemo.getConnection();
			Statement state = conn.createStatement();
			String sql = "select empno,sal from emp where empno = (select mgr from emp where empno = " + empno + ")";
			ResultSet rs = state.executeQuery(sql);
			if(rs.next()){
				int en = rs.getInt("empno");
				double sal = rs.getDouble("sal");
				System.out.println("领导编号:" + en + ",领导工资:" + sal);
			}else{
				System.out.println("查找失败,没有相关的领导!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButilDemo.closeConnection(conn);//释放资源
		}
	}
}
