package com.gk.spring02;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test02 {
	@Test
	public void test01(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		Man m = (Man)ctx.getBean("m");
		m.work();
	}
}
