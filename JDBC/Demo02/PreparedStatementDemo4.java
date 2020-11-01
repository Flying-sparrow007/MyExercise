package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;

/**
 * ������:
 * �������ǿ��Խ�Ҫִ�еĴ���SQL��仺���ڱ���,Ȼ��һ���Է���
 * �����ݿ�,�����������,���ִ��Ч��
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
				ps.addBatch();//�洢����������
				//����6�����ݷ���
				if(i % 6 == 0){
					//�ѻ��浽���е�����ȫ�����͸����ݿ�,ÿ����һ������һ��int����
					int[] arr = ps.executeBatch();//���ͻ�������
					ps.clearBatch();//���������
					System.out.println(Arrays.toString(arr));
					System.out.println("�������ݷ������!");
				}
			}
			//����ʣ������
			ps.executeBatch();
			System.out.println("ʣ�������ѷ���!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DButil.closeConnection(conn, null, ps);
		}
		
	}
}
