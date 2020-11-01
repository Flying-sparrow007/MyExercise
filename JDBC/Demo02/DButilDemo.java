package day02;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * 此工具类负责连接数据库和释放连接资源
 * @author LENOVO
 *
 */
public class DButilDemo {
	//MySQL驱动
	private static String driver;
	//地址
	private static String url;
	//账号
	private static String username;
	//密码
	private static String password;
	
	static{
		/*Properties:该类主要用于读取Java的配置文件,不同的编程
		语言有自己所支持的配置文件*/
		try {
			Properties p = new Properties();
			//方式一:
			//p.load(new FileInputStream("src/main/resources/db.properties"));
			//方式二
			//读取加载资源/文件
			p.load(DButilDemo.class.getClassLoader().getResourceAsStream("db.properties"));
			driver = p.getProperty("drivername");
			url = p.getProperty("url");
			username = p.getProperty("username");
			password = p.getProperty("userpwd");
			System.out.println("连接数据库初始化...");
			System.out.println(driver);
			System.out.println(url);
			System.out.println(username);
			System.out.println(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取数据库连接
	public static Connection getConnection(){
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("连接成功!");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("连接失败,请联系相关工作人员...");
		}
	}
	
	//释放资源
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
