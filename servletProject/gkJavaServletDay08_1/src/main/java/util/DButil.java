package util;
/**
 * 数据库连接的管理
 * DBCP(DataBase connection poll)数据库连接池是一个Java连接池项目,DBCP通过连接吃
 * 预先同数据库建立一个连接放在内存中(即连接池中),应用程序需要建立数据库连接时直接
 * 到连接池中申请一个连接使用,用完后有连接池回收该连接,从而达到连接的复用,减少资源消耗的目的.
 * 
 * 连接池来管理Connection,这样可以重复使用Connection,有了连接池,所以我们就不用自己来创建
 * Connection,而是通过池来获取Connection对象,当使用完Connection的close方法也不会真的关闭
 * Connection,而是把Connection"归还"给连接池,池就可以再次利用这个Connection对象
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
	//连接池对象
	private static BasicDataSource ds;
	
	static{
		try {
			/*Properties:该类主要用于读取Java的配置文件,不同的编程
			语言有自己所支持的配置文件*/
			Properties p = new Properties();
			//读取加载资源/文件
			p.load(DButil.class.getClassLoader().getResourceAsStream("db.properties"));
			//获取MySQL驱动
			String driver = p.getProperty("drivername");
			//获取连接地址
			String url = p.getProperty("url");
			//获取连接数据库用户名
			String username = p.getProperty("username");
			//获取连接数据库密码
			String password = p.getProperty("userpwd");
			//获取最大连接
			int maxActive = Integer.parseInt(p.getProperty("maxActive"));
			//获取最大等待时间
			int maxWait = Integer.parseInt(p.getProperty("maxWait"));
			System.out.println("初始化数据库...");
			System.out.println(driver);
			System.out.println(url);
			System.out.println(username);
			System.out.println(password);
			System.out.println(maxActive);
			System.out.println(maxWait);
			
			//创建数据库连接池
			ds = new BasicDataSource();
			
			//Class.forName()设置MySQL驱动
			ds.setDriverClassName(driver);
			
			//DriverManager.getConnection(...)设置连接数据库地址,用户名,密码
			ds.setUrl(url);
			ds.setUsername(username);
			ds.setPassword(password);
			
			//设置最大连接数
			ds.setMaxActive(maxActive);
			
			//设置最大等待时间
			ds.setMaxWait(maxWait);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取数据库连接
	public static Connection getConnection(){
		try {
			/*
			 * 向连接池获取连接,若连接池中没有可用连接时,该方法会阻塞当前线程.
			 * 阻塞时间由连接池设置的maxWait决定,当阻塞过程中有了可用连接时
			 * 则会立即返回,若超时仍然没有可用连接,该方法会抛出异常 
			 * 
			 * 数据库连接池给我们提供了方法getConnection()从数据库连接池中获取连接
			 */
			Connection conn = ds.getConnection();
			System.out.println("连接成功!");
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("连接异常,稍后再重试");
		}
	}
	
	//归还资源
	public static void closeConnection(Connection conn, ResultSet rs, PreparedStatement ps){
		try {
			if(conn != null){
				/*
				 * 连接池的连接对于close方法的处理是将连接在连接池中的状态
				 * 设置为空闲,而不是真的将其关闭
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
