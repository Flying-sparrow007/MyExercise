package day02;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * �˹����ฺ���������ݿ���ͷ�������Դ
 * @author LENOVO
 *
 */
public class DButilDemo {
	//MySQL����
	private static String driver;
	//��ַ
	private static String url;
	//�˺�
	private static String username;
	//����
	private static String password;
	
	static{
		/*Properties:������Ҫ���ڶ�ȡJava�������ļ�,��ͬ�ı��
		�������Լ���֧�ֵ������ļ�*/
		try {
			Properties p = new Properties();
			//��ʽһ:
			//p.load(new FileInputStream("src/main/resources/db.properties"));
			//��ʽ��
			//��ȡ������Դ/�ļ�
			p.load(DButilDemo.class.getClassLoader().getResourceAsStream("db.properties"));
			driver = p.getProperty("drivername");
			url = p.getProperty("url");
			username = p.getProperty("username");
			password = p.getProperty("userpwd");
			System.out.println("�������ݿ��ʼ��...");
			System.out.println(driver);
			System.out.println(url);
			System.out.println(username);
			System.out.println(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//��ȡ���ݿ�����
	public static Connection getConnection(){
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("���ӳɹ�!");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("����ʧ��,����ϵ��ع�����Ա...");
		}
	}
	
	//�ͷ���Դ
	public static void closeConnection(Connection conn){
		try {
			if(conn != null){
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Connection conn = DButilDemo.getConnection();
		System.out.println(conn);
	}
	
}
