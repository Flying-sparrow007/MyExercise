package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * �Զ����ز������ݵ�����
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
			//����prapareStatement��ͬʱָ��ִ�и�prepareStatement��Ӧ��SQL���Ҫ
			//�õ������¼��ָ�����ֶε�ֵ(һ�����ǻ��ȡ��������ݵ�����id)
			ps = conn.prepareStatement(sql, new String[]{"id"});
			ps.setString(1, "admin");
			ps.setString(2, "123456");
			ps.setString(3, "��");
			int n = ps.executeUpdate();
			if(n > 0){
				System.out.println("����ɹ�!");
				rs = ps.getGeneratedKeys();//��ȡ����
				if(rs.next()){
					int id = rs.getInt(1);
					System.out.println(id);
					//��������ȡ��id��ѯ������
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
				System.out.println("����ʧ��!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.closeConnection(conn, rs, ps);
		}
	}
}
