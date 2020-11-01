package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * 批量执行相同的SQL语句,ps可以重用执行计划,减少数据库开销
 * @author LENOVO
 *
 */
public class PreparedStatementDemo2 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DButil.getConnection();
			long time1 = System.currentTimeMillis();
			/*Statement state = conn.createStatement();
			for(int i = 1; i <= 10000; ++i){
				String sql = "insert into myjdbc (user_name) values ('ceshi" + i + "')";
				state.execute(sql);//执行SQL语句100次
			}*/
			
			String sql = "insert into myjdbc (user_name) values (?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			for(int i = 1; i <= 10000; ++i){
				ps.setString(1, "测试" + i);
				ps.executeUpdate();
			}
			
			long time2 = System.currentTimeMillis();
			System.out.println("插入成功!消耗时间为:" + (time2 - time1) + "毫秒");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.closeConnection(conn, null, null);
		}
	}
}
