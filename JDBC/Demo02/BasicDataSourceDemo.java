package day02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 测试连接池最大连接和最大等待时间
 * @author LENOVO
 *
 */
public class BasicDataSourceDemo {
	public static void main(String[] args) {
		Thread t1 = new DemoThread(5000, "连接1");
		Thread t2 = new DemoThread(6000, "连接2");
		Thread t3 = new DemoThread(7000, "连接3");
		
		t1.start();
		t2.start();
		t3.start();
	}
	
}

class DemoThread extends Thread{
	private int wait;//睡眠时间
	private String name;//连接名字
	
	public DemoThread(int wait, String name){
		this.wait = wait;
		this.name = name;
	}
	
	public void run(){
		Connection conn = null;
		ResultSet rs = null;
		try {
			//数据库连接
			conn = DButil.getConnection();
			System.out.println(name + "连接");
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