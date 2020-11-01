package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 自动返回插入数据的主键
 * @author LENOVO
 *
 */
public class PreparedStatementDemo5 {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DButil.getConnection();
			String sql = "insert into myjdbc values (null, ?, ?, ?)";
			//创建prapareStatement的同时指定执行该prepareStatement对应的SQL语句要
			//得到插入记录中指定的字段的值(一般我们会获取插入该数据的主键id)
			ps = conn.prepareStatement(sql, new String[]{"id"});
			ps.setString(1, "admin");
			ps.setString(2, "123456");
			ps.setString(3, "男");
			int n = ps.executeUpdate();
			if(n > 0){
				System.out.println("插入成功!");
				rs = ps.getGeneratedKeys();//获取主键
				if(rs.next()){
					int id = rs.getInt(1);
					System.out.println(id);
					//根据所获取的id查询此数据
					String sql2 = "select * from myjdbc where id = ?";
					ps = conn.prepareStatement(sql2);
					ps.setInt(1, id);
					rs = ps.executeQuery();
					if(rs.next()){
						int i = rs.getInt("id");
						String name = rs.getString("user_name");
						String pwd = rs.getString("password");
						char gender = rs.getString("gender").charAt(0);
						System.out.println(i + "," + name + "," + pwd + "," + gender);
					}
				}
			}else{
				System.out.println("插入失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.closeConnection(conn, rs, ps);
		}
	}
}
