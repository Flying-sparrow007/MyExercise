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
		
	}
	
	@Test
	public void testFindAllUser(){
		List<User> list = session.selectList("test.findAllUser");
		System.out.println(list);
	}
	
	@Test
	public void testAddUser(){
		User user = new User();
		user.setUser_name("����");
		user.setUser_password("123456");
		user.setSal(1230.2);
		user.setUser_age(23);
		session.insert("addUser", user);
		
		//��ʽ��:�ύ����(���,ɾ��,�޸�)
		session.commit();
		//�ͷ���Դ
		session.close();
	}
	
	@Test
	public void testDeleteUserById(){
		session.delete("deleteUserById", 8);
		session.commit();
		//�ͷ���Դ
		session.close();
	}
	
	@Test
	public void testUpdateUserById(){
		User user = new User();
		user.setId(9);
		user.setUser_name("�ǹ�");
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
		user.setUserName("�߹�");
		user.setUserPassword("123456");
		user.setUserSal(12543.02);
		user.setUserAge(18);
		session.insert("addUser2", user);
		session.commit();
		session.close();
	}
}
