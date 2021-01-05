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
import mapper.UserDao;

public class TestUserDao {
	SqlSession session;
	UserDao dao;
	
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
		dao = session.getMapper(UserDao.class);
	}
	
	@Test
	public void testFindAllUser(){
		/*List<User> list2 = session.selectList("findAllUser");
		System.out.println(list2);*/
		
		//sqlSession提供了获取映射器的方法getMapper(T t)
		List<User> list = dao.findAllUser();
		System.out.println(list);
	}
	
	@Test
	public void testFindUserById(){
		User user = dao.findUserById(9);
		System.out.println(user);
	}
	
	@Test
	public void testAddUser(){
		User user = new User();
		user.setUser_name("gk");
		user.setUser_password("123456");
		user.setSal(21365.32);
		user.setUser_age(22);
		int n = dao.addUser(user);
		System.out.println(n);
		session.commit();
		session.close();
	}
	
	@Test
	public void testDeleteUserById(){
		int n = dao.deleteUserById(9);
		System.out.println(n);
		session.commit();
		session.close();
	}
	
	@Test
	public void testUpdateUserById(){
		User user = new User();
		user.setId(11);
		user.setUser_name("李四");
		user.setUser_password("666666");
		int n = dao.updateUserById(user);
		System.out.println(n);
		session.commit();
		session.close();
	}
	
	@Test
	public void testUpdateUserById2(){
		int n = dao.updateUserById2("123456", 11);
		session.commit();
		session.close();
		System.out.println(n);
	}
}
