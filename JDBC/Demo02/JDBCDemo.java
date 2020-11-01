package day02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 * ������ϰ:
 * ���ݲ��ű�Ų�ѯ�ò��ŵ�ƽ������,��͹���,��߹���,�����ܺ�
 * @author LENOVO
 *
 */
public class JDBCDemo {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DButilDemo.getConnection();//��������
			Scanner scanner = new Scanner(System.in);
			System.out.println("�����벿�ű��:");
			int deptno = scanner.nextInt();
			Statement state = conn.createStatement();//��ȡִ��SQL����Statementʵ��
			String sql = "select avg(sal),min(sal),max(sal),sum(sal) from emp where deptno = " + deptno;
			ResultSet rs = state.executeQuery(sql);
			if(rs.next()){
				double avg = rs.getDouble("avg(sal)");
				double min = rs.getDouble("min(sal)");
				double max = rs.getDouble("max(sal)");
				double sum = rs.getDouble("sum(sal)");
				System.out.println(deptno + "�Ų��ŵ�ƽ������:" + avg + ",��ڹ���:" + min + ",��߹���:" + max + ",�����ܺ�:" + sum);
			}else{
				System.out.println("û����صĲ���");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButilDemo.closeConnection(conn);
		}
	}
}
