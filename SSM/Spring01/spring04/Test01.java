package com.gk.spring04;

import java.sql.Connection;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test01 {
	@Test
	public void test01(){
		System.out.println("数据库连接中...");
		ApplicationContext a = new ClassPathXmlApplicationContext("applicationContext3.xml");
		Connection conn = a.getBean("conn", Connection.class);
		System.out.println("连接成功!");
	}
}
