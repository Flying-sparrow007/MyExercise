package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * �������ݿ�
 * @author LENOVO
 *
 */
public class DButil{
	private static BasicDataSource bs;//���ݿ����ӳ�
	
	static{
		try {
			Properties p = new Properties();
			//����db.properties�ļ�
			p.load(DButil.class.getClassLoader().getResourceAsStream("db.properties"));
			//��ȡ���ݿ�����
			String driver = p.getProperty("driver");
			//��ȡ���ݿ�url
			String url = p.getProperty("url");
			//��ȡ�û�����
			String name = p.getProperty("name");
			//��ȡ���ݿ�����
			String password = p.getProperty("password");
			//��ȡ���ݿ����ӳ��������
			int maxActive = Integer.parseInt(p.getProperty("maxActive"));
			//��ȡ���ݿ����ӳصȴ�ʱ��
			long maxWait = Integer.parseInt(p.getProperty("maxWait"));
			
			//�������ݿ����ӳ�
			bs = new BasicDataSource();
			
			//�������ݿ����ӳ���ز���
			bs.setDriverClassName(driver);
			bs.setUrl(url);
			bs.setUsername(name);
			bs.setPassword(password);
			bs.setMaxActive(maxActive);
			bs.setMaxWait(maxWait);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**�������ݿ�*/
	public static Connection getConnection(){
		try {
			//System.out.println("�������ݿ�...");
			Connection conn = bs.getConnection();
			//System.out.println("���ӳɹ�!");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("�������쳣,����ϵ������Ա...");
		}
	}
	
	/**�ر�����*/
	public static void close(Connection conn, PreparedStatement ps, ResultSet rs){
		try {
			if(conn != null){
				conn.close();
			}
			if(ps != null){
				ps.close();
			}
			if(rs != null){
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("�ر������쳣,����ϵ������Ա...");
		}
	}
	
	
	public static void main(String[] args) {
		DButil.getConnection();
	}
}
