package com.gk.spring03;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test01 {
	@Test
	public void test01(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext2.xml");
		//����ʹ��spring�����ṩ�����ǵ�getBean(String bean, Object.class)��ʽ����ǿת
		Calendar c = ctx.getBean("cal", Calendar.class);
		Date date = c.getTime();
		System.out.println(date);
	}
	
	@Test
	public void test02(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext2.xml");
		//����ʹ��spring�����ṩ�����ǵ�getBean(String bean, Object.class)��ʽ����ǿת
		Date date = ctx.getBean("date", Date.class);
		System.out.println(date);
	}
}
