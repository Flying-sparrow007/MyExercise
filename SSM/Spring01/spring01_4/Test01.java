package com.gk.spring04;

import java.sql.Connection;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test01 {
	@Test
	public void test01(){
		System.out.println("���ݿ�������...");
		ApplicationContext a = new ClassPathXmlApplicationContext("applicationContext3.xml");
		Connection conn = a.getBean("conn", Connection.class);
		System.out.println("���ӳɹ�!");
	}
}
