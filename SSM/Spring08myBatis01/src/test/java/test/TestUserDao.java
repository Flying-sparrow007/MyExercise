package test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import entity.User;
import entity.User2;

public class TestUserDao {
	SqlSession session;
	
	@Before
	public void init() throws IOException{
		//加载Mybatis核心配置文件
		InputStream in = Resources.getResourceAsStream("mybatis.xml");
		//构建一个数据源工厂
		SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
		/*
		 * SqlSessionFactory理解成数据源,相当于JDBC中的DataSource
		 * 以下内容需了解即可:
		 * 1.SqlSessionFactory是线程安全的,可以让多个执行线程同时存取,
		 * SqlSessionFactory而不会有数据共享问题
		 * 2.SqlSessionFactory(单例模式)当操作多个数据库时,可以为每个数据库
		 * 指定一个SqlSessionFactory
		 */
		SqlSessionFactory ssf = ssfb.build(in);
		//获取SqlSession对象,理解成Connection的封装
		//方式一:提交事务(添加,删除,修改)
		//session = ssf.openSession(true);
		session = ssf.openSession();
		
	}
	
	@Test
	public void testFindAllUser(){
		List<User> list = session.selectList("test.findAllUser");
		System.out.println(list);
	}
	
	@Test
	public void testAddUser(){
		User user = new User();
		user.setUser_name("张三");
		user.setUser_password("123456");
		user.setSal(1230.2);
		user.setUser_age(23);
		session.insert("addUser", user);
		
		//方式二:提交事务(添加,删除,修改)
		session.commit();
		//释放资源
		session.close();
	}
	
	@Test
	public void testDeleteUserById(){
		session.delete("deleteUserById", 8);
		session.commit();
		//释放资源
		session.close();
	}
	
	@Test
	public void testUpdateUserById(){
		User user = new User();
		user.setId(9);
		user.setUser_name("呵哈");
		user.setUser_password("666666");
		session.update("updateUserById", user);
		session.commit();
		session.close();
	}
	
	@Test
	public void testFindUserById(){
		Map<String, Object> map = session.selectOne("findUserById", 11);
		System.out.println(map.size());
	}
	
	@Test
	public void testFindUserById2(){
		User2 user2 = session.selectOne("findUserById2", 11);
		System.out.println(user2);
	}
	
	@Test
	public void testAddUser2(){
		User2 user = new User2();
		user.setUserName("哼哈");
		user.setUserPassword("123456");
		user.setUserSal(12543.02);
		user.setUserAge(18);
		session.insert("addUser2", user);
		session.commit();
		session.close();
	}
}
