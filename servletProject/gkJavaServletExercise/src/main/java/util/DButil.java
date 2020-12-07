package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * 连接数据库
 * @author LENOVO
 *
 */
public class DButil{
	private static BasicDataSource bs;//数据库连接池
	
	static{
		try {
			Properties p = new Properties();
			//加载db.properties文件
			p.load(DButil.class.getClassLoader().getResourceAsStream("db.properties"));
			//获取数据库驱动
			String driver = p.getProperty("driver");
			//获取数据库url
			String url = p.getProperty("url");
			//获取用户密码
			String name = p.getProperty("name");
			//获取数据库密码
			String password = p.getProperty("password");
			//获取数据库连接池最大数量
			int maxActive = Integer.parseInt(p.getProperty("maxActive"));
			//获取数据库连接池等待时间
			long maxWait = Integer.parseInt(p.getProperty("maxWait"));
			
			//创建数据库连接池
			bs = new BasicDataSource();
			
			//设置数据库连接池相关参数
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
	
	/**连接数据库*/
	public static Connection getConnection(){
		try {
			//System.out.println("连接数据库...");
			Connection conn = bs.getConnection();
			//System.out.println("连接成功!");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("服务器异常,请联系工作人员...");
		}
	}
	
	/**关闭连接*/
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
			throw new RuntimeException("关闭连接异常,请联系工作人员...");
		}
	}
	
	
	public static void main(String[] args) {
		DButil.getConnection();
	}
}
