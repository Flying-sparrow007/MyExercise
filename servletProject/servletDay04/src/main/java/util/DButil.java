package util;
/**
 * ���ݿ����ӵĹ���
 * DBCP(DataBase connection poll)���ݿ����ӳ���һ��Java���ӳ���Ŀ,DBCPͨ�����ӳ�
 * Ԥ��ͬ���ݿ⽨��һ�����ӷ����ڴ���(�����ӳ���),Ӧ�ó�����Ҫ�������ݿ�����ʱֱ��
 * �����ӳ�������һ������ʹ��,����������ӳػ��ո�����,�Ӷ��ﵽ���ӵĸ���,������Դ���ĵ�Ŀ��.
 * 
 * ���ӳ�������Connection,���������ظ�ʹ��Connection,�������ӳ�,�������ǾͲ����Լ�������
 * Connection,����ͨ��������ȡConnection����,��ʹ����Connection��close����Ҳ������Ĺر�
 * Connection,���ǰ�Connection"�黹"�����ӳ�,�ؾͿ����ٴ��������Connection����
 * @author LENOVO
 *
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class DButil{
	//���ӳض���
	private static BasicDataSource ds;
	
	static{
		try {
			/*Properties:������Ҫ���ڶ�ȡJava�������ļ�,��ͬ�ı��
			�������Լ���֧�ֵ������ļ�*/
			Properties p = new Properties();
			//��ȡ������Դ/�ļ�
			p.load(DButil.class.getClassLoader().getResourceAsStream("db.properties"));
			//��ȡMySQL����
			String driver = p.getProperty("drivername");
			//��ȡ���ӵ�ַ
			String url = p.getProperty("url");
			//��ȡ�������ݿ��û���
			String username = p.getProperty("username");
			//��ȡ�������ݿ�����
			String password = p.getProperty("userpwd");
			//��ȡ�������
			int maxActive = Integer.parseInt(p.getProperty("maxActive"));
			//��ȡ���ȴ�ʱ��
			int maxWait = Integer.parseInt(p.getProperty("maxWait"));
			System.out.println("��ʼ�����ݿ�...");
			System.out.println(driver);
			System.out.println(url);
			System.out.println(username);
			System.out.println(password);
			System.out.println(maxActive);
			System.out.println(maxWait);
			
			//�������ݿ����ӳ�
			ds = new BasicDataSource();
			
			//Class.forName()����MySQL����
			ds.setDriverClassName(driver);
			
			//DriverManager.getConnection(...)�����������ݿ��ַ,�û���,����
			ds.setUrl(url);
			ds.setUsername(username);
			ds.setPassword(password);
			
			//�������������
			ds.setMaxActive(maxActive);
			
			//�������ȴ�ʱ��
			ds.setMaxWait(maxWait);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//��ȡ���ݿ�����
	public static Connection getConnection(){
		try {
			/*
			 * �����ӳػ�ȡ����,�����ӳ���û�п�������ʱ,�÷�����������ǰ�߳�.
			 * ����ʱ�������ӳ����õ�maxWait����,���������������˿�������ʱ
			 * �����������,����ʱ��Ȼû�п�������,�÷������׳��쳣 
			 * 
			 * ���ݿ����ӳظ������ṩ�˷���getConnection()�����ݿ����ӳ��л�ȡ����
			 */
			Connection conn = ds.getConnection();
			System.out.println("���ӳɹ�!");
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("�����쳣,�Ժ�������");
		}
	}
	
	//�黹��Դ
	public static void closeConnection(Connection conn, ResultSet rs, PreparedStatement ps){
		try {
			if(conn != null){
				/*
				 * ���ӳص����Ӷ���close�����Ĵ����ǽ����������ӳ��е�״̬
				 * ����Ϊ����,��������Ľ���ر�
				 */
				conn.close();
			}
			if(rs != null){
				rs.close();
			}
			if(ps != null){
				ps.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Connection conn = DButil.getConnection();
		System.out.println(conn);
	}
	
}
