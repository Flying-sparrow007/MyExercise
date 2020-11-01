package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;

/**
 * 批操作:
 * 批操作是可以将要执行的大量SQL语句缓存在本地,然后一次性发送
 * 给数据库,减少网络调用,提高执行效率
 * @author LENOVO
 *
 */
public class PreparedStatementDemo4 {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DButil.getConnection();
			String sql = "insert into bank values (null, ?, ?)";
			ps = conn.prepareStatement(sql);
			for(int i = 1; i <= 10; ++i){
				ps.setString(1, "ceshi" + i);
				ps.setDouble(2, 2000 + i);
				ps.addBatch();//存储到缓存批中
				//缓存6条数据发送
				if(i % 6 == 0){
					//把缓存到批中的数据全部发送给数据库,每插入一条返回一个int类型
					int[] arr = ps.executeBatch();//发送缓存数据
					ps.clearBatch();//清除缓存批
					System.out.println(Arrays.toString(arr));
					System.out.println("缓存数据发送完毕!");
				}
			}
			//发送剩余数据
			ps.executeBatch();
			System.out.println("剩余数据已发送!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DButil.closeConnection(conn, null, ps);
		}
		
	}
}
