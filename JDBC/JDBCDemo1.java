package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC��SUN�ṩ��һ�����ڲ������ݿ�ı�׼�ӿ�,��ͬ�����ݿ⳧�̶��ṩ��
 * һ��JDBC�ӿڵ�ʵ��,���һ��jar������,��������������.
 * 
 * JDBC�ӿ����ṩ��:
 * DriverManager:�������������������ݿ�����
 * Connection:��ʾ�����ݿ������,�����������,����ִ��SQL���Ķ���Statement;
 * Statement:����ִ��SQL���,��ִ�е��ǲ�ѯ���,��õ���ѯ�Ľ����ResultSet;
 * ResultSet:��ʾ��ѯ�Ľ����,����������ɻ�ò�ѯ�ľ�������;
 * @author LENOVO
 *
 */
public class JDBCDemo1 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			//1.����������,��ͬ�����ݿ����ֵ��һ��
			Class.forName("com.mysql.jdbc.Driver");
			
			//2.��������,��ͬ�����ݿ��в�ͬ�����Ӹ�ʽ
			//����:MySQL���ݿ��ַ jdbc:mysql://���ݿ�IP��ַ:�˿ں�/���ݿ���
			String url = "jdbc:mysql://localhost:3306/store_ykt";
			
			//3.���ݿ������˺�
			String username = "root";
			
			//4.���ݿ���������
			String password = "984264";
			
			/*
			 * DriverManager�ṩ���������ݿ�ķ���
			 * getConnection(url, user, password)����Connection
			 * 
			 */
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("��������!");
			//ͨ��Connection��������ִ��SQL����Statementʵ��
			Statement state = conn.createStatement();
			String sql = "create table myjdbc(id int primary key auto_increment,user_name varchar(10),password varchar(10),gender char(1))";
			/*
			 * Statementִ��SQL������ط���
			 * int executeUpdate(String sql)(�������ݿ����,�޸�,ɾ��)
			 * ������1��ִ�гɹ�,����0��ִ��ʧ��,����ר������ִ��DML���
			 * 
			 * ResultSet executeQuery(String sql)(��ѯ)ר����ִ��DQL���,���ز�ѯ�Ľ����,
			 * ��ResultSetʵ������
			 * 
			 * boolean execute(String sql)(����)����ִ���κ����͵�SQL���,������DML��DQL����ר��
			 * �ķ���ִ��,���Ը÷���һ������ִ��DDL,����ֵΪִ�к��Ƿ��н��
			 */
			//��Statementִ��SQL���
			state.execute(sql);
			System.out.println("�������!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			//�ͷ���Դ
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}