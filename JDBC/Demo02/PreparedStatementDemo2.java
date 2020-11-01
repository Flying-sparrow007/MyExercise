package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * ����ִ����ͬ��SQL���,ps��������ִ�мƻ�,�������ݿ⿪��
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
				state.execute(sql);//ִ��SQL���100��
			}*/
			
			String sql = "insert into myjdbc (user_name) values (?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			for(int i = 1; i <= 10000; ++i){
				ps.setString(1, "����" + i);
				ps.executeUpdate();
			}
			
			long time2 = System.currentTimeMillis();
			System.out.println("����ɹ�!����ʱ��Ϊ:" + (time2 - time1) + "����");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.closeConnection(conn, null, null);
		}
	}
}
