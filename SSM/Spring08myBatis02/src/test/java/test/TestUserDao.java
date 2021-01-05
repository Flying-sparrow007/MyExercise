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
		//����Mybatis���������ļ�
		InputStream in = Resources.getResourceAsStream("mybatis.xml");
		//����һ������Դ����
		SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
		/*
		 * SqlSessionFactory��������Դ,�൱��JDBC�е�DataSource
		 * �����������˽⼴��:
		 * 1.SqlSessionFactory���̰߳�ȫ��,�����ö��ִ���߳�ͬʱ��ȡ,
		 * SqlSessionFactory�����������ݹ�������
		 * 2.SqlSessionFactory(����ģʽ)������������ݿ�ʱ,����Ϊÿ�����ݿ�
		 * ָ��һ��SqlSessionFactory
		 */
		SqlSessionFactory ssf = ssfb.build(in);
		//��ȡSqlSession����,����Connection�ķ�װ
		//��ʽһ:�ύ����(���,ɾ��,�޸�)
		//session = ssf.openSession(true);
		session = ssf.openSession();
		dao = session.getMapper(UserDao.class);
	}
	
	@Test
	public void testFindAllUser(){
		/*List<User> list2 = session.selectList("findAllUser");
		System.out.println(list2);*/
		
		//sqlSession�ṩ�˻�ȡӳ�����ķ���getMapper(T t)
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
		user.setUser_name("����");
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
