package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 * 转账功能
 * 输入转出账户的用户名,再输入转入账号的用户名
 * 最后输入要转账的金额完成转账功能
 * @author LENOVO
 *
 */
public class PreparedStatementDemo3 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入转出账号名字:");
		String outName = scanner.next();
		System.out.println("请输入转入账号名字:");
		String inName = scanner.next();
		System.out.println("请输入转账金额:");
		double price = scanner.nextDouble();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DButil.getConnection();
			/*
			 * JDBC默认是自动提交事务,也就是当执行一条SQL操作后都会自动提交事务
			 * 若希望手动控制事务,需要将自动提交事务关闭
			 */
			//设置手动提交事务
			conn.setAutoCommit(false);
			String sql = "update bank set money = money - ? where name = ?";
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, price);
			ps.setString(2, outName);
			int n = ps.executeUpdate();
			if(n > 0){//转出成功
				String sql2 = "update bank set money = money + ? where name = ?";
				ps = conn.prepareStatement(sql2);
				ps.setDouble(1, price);
				ps.setString(2, inName);
				int n2 = ps.executeUpdate();
				if(n2 > 0){//转入成功
					System.out.println("转帐成功!");
					//手动提交
					conn.commit();
				}else{//事务回滚
					System.out.println("转账失败,事务回滚!");
					conn.rollback();
				}
			}else{
				System.out.println("该用户不存在!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("转账失败!");
		} finally {
			DButil.closeConnection(conn, null, ps);
		}
	}
}
