package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 * Statement�ʺ�ִ�о�̬SQL���Ҳ����SQL�����û��ƴ�Ӷ�̬����
 * 
 * PreparedStatementʱJava.sql�������һ���ӿ�,����ִ��SQL����ѯ
 * ͨ������connection.preparedStatement(sql)�������Ի�ȡPreparedStatement����,
 * ���ݿ�ϵͳ���sql������Ԥ�ദ��.
 * ������佫��Ԥ�ȱ����,���������SQL��ѯ�������ٽ����Ĳ�ѯ������
 * ����һ��,����Statement�������ɵĲ�ѯ���ٶȸ���
 * 
 * Statementʹ��Statement����,�ڶ����ݿ�ִֻ��һ���Դ�ȡ��ʱ��,��
 * Statement������д���
 * 
 * PreparedStatement����Ŀ�����Statement��,����һ���Բ���������
 * ��������ĺô�,ѡ��PreparedStatement����Statementȡ������Ҫ��ôʹ��
 * ����,����ִֻ��һ�ε�SQL���ѡ��Statement����õ�,�෴��SQLִ�б�
 * ���ִ��ѡ����PreparedStatement����õ�
 * 
 * PreparedStatement�ĵ�һ��ִ�������Ǻܸߵ�,�������������ں�����ظ�ִ��
 * @author LENOVO
 *
 */
public class PreparedStatementDemo {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("�������û���:");
		String name = scanner.next();
		System.out.println("����������:");
		String pwd = scanner.next();
		System.out.println("�������Ա�:");
		String gender = scanner.next();
		Connection conn = null;
		try {
			//���ݿ�����
			conn = DButil.getConnection();
			//ͨ��Connection����PreparedStatement����
			String sql = "insert into myjdbc values (null,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, pwd);
			ps.setString(3, gender);
			int n = ps.executeUpdate();
			if(n > 0){
				System.out.println("����ɹ�!");
			}else{
				throw new RuntimeException("����ʧ��!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.closeConnection(conn, null, null);
		}
	}
}
