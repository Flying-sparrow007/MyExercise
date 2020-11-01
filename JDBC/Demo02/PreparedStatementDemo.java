package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 * Statement适合执行静态SQL语句也就是SQL语句中没有拼接动态数据
 * 
 * PreparedStatement时Java.sql包下面的一个接口,用来执行SQL语句查询
 * 通过调用connection.preparedStatement(sql)方法可以获取PreparedStatement对象,
 * 数据库系统会对sql语句进行预编处理.
 * 处理语句将被预先编译好,这条编译的SQL查询语句可能再将来的查询中重用
 * 这样一来,它比Statement对象生成的查询你速度更快
 * 
 * Statement使用Statement对象,在对数据库只执行一次性存取的时候,用
 * Statement对象进行处理
 * 
 * PreparedStatement对象的开销比Statement大,对于一次性操作并不会
 * 带来额外的好处,选择PreparedStatement还是Statement取决于你要怎么使用
 * 它们,对于只执行一次的SQL语句选择Statement是最好的,相反用SQL执行被
 * 多次执行选择用PreparedStatement是最好的
 * 
 * PreparedStatement的第一次执行消耗是很高的,它的性能体现在后面的重复执行
 * @author LENOVO
 *
 */
public class PreparedStatementDemo {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入用户名:");
		String name = scanner.next();
		System.out.println("请输入密码:");
		String pwd = scanner.next();
		System.out.println("请输入性别:");
		String gender = scanner.next();
		Connection conn = null;
		try {
			//数据库连接
			conn = DButil.getConnection();
			//通过Connection创建PreparedStatement对象
			String sql = "insert into myjdbc values (null,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, pwd);
			ps.setString(3, gender);
			int n = ps.executeUpdate();
			if(n > 0){
				System.out.println("插入成功!");
			}else{
				throw new RuntimeException("插入失败!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.closeConnection(conn, null, null);
		}
	}
}
