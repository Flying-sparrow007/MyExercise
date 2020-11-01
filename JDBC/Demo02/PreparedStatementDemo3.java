package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 * ת�˹���
 * ����ת���˻����û���,������ת���˺ŵ��û���
 * �������Ҫת�˵Ľ�����ת�˹���
 * @author LENOVO
 *
 */
public class PreparedStatementDemo3 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("������ת���˺�����:");
		String outName = scanner.next();
		System.out.println("������ת���˺�����:");
		String inName = scanner.next();
		System.out.println("������ת�˽��:");
		double price = scanner.nextDouble();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DButil.getConnection();
			/*
			 * JDBCĬ�����Զ��ύ����,Ҳ���ǵ�ִ��һ��SQL�����󶼻��Զ��ύ����
			 * ��ϣ���ֶ���������,��Ҫ���Զ��ύ����ر�
			 */
			//�����ֶ��ύ����
			conn.setAutoCommit(false);
			String sql = "update bank set money = money - ? where name = ?";
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, price);
			ps.setString(2, outName);
			int n = ps.executeUpdate();
			if(n > 0){//ת���ɹ�
				String sql2 = "update bank set money = money + ? where name = ?";
				ps = conn.prepareStatement(sql2);
				ps.setDouble(1, price);
				ps.setString(2, inName);
				int n2 = ps.executeUpdate();
				if(n2 > 0){//ת��ɹ�
					System.out.println("ת�ʳɹ�!");
					//�ֶ��ύ
					conn.commit();
				}else{//����ع�
					System.out.println("ת��ʧ��,����ع�!");
					conn.rollback();
				}
			}else{
				System.out.println("���û�������!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ת��ʧ��!");
		} finally {
			DButil.closeConnection(conn, null, ps);
		}
	}
}
