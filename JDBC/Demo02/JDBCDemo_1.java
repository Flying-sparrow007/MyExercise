package day02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 课堂练习2:
 * 查询有商品的分类信息的parent_id
 * @author LENOVO
 *
 */
public class JDBCDemo_1 {
	public static void main(String[] args) {
		Connection conn  = null;
		try {
			conn = DButilDemo.getConnection();
			
			Statement state = conn.createStatement();
			
			String sql = "SELECT t.category_id,t.title,tc.name FROM t_item t JOIN t_item_category tc ON t.category_id = tc.id WHERE category_id IN (SELECT id FROM t_item_category)";
			ResultSet rs = state.executeQuery(sql);
			
			while(rs.next()){
				int id = rs.getInt("category_id");
				String title = rs.getString("title");
				String name = rs.getString("name");
				System.out.println(id + "," + title + "," + name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DButilDemo.closeConnection(conn);
		}
		
	}
}
