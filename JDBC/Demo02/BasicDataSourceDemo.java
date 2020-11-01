package day02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * �������ӳ�������Ӻ����ȴ�ʱ��
 * @author LENOVO
 *
 */
public class BasicDataSourceDemo {
	public static void main(String[] args) {
		Thread t1 = new DemoThread(5000, "����1");
		Thread t2 = new DemoThread(6000, "����2");
		Thread t3 = new DemoThread(7000, "����3");
		
		t1.start();
		t2.start();
		t3.start();
	}
	
}

class DemoThread extends Thread{
	private int wait;//˯��ʱ��
	private String name;//��������
	
	public DemoThread(int wait, String name){
		this.wait = wait;
		this.name = name;
	}
	
	public void run(){
		Connection conn = null;
		ResultSet rs = null;
		try {
			//���ݿ�����
			conn = DButil.getConnection();
			System.out.println(name + "����");
			Statement state = conn.createStatement();
			String sql = "select * from myjdbc";
			rs = state.executeQuery(sql);
			while(rs.next()){
				System.out.println(rs.getString("user_name") + "," + rs.getString("gender"));
			}
			Thread.sleep(wait);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.closeConnection(conn, rs, null);
		}
		
		
	}
}