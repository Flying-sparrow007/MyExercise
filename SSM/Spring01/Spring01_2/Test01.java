package com.gk.spring02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test01 {
	public static void main(String[] args) {
		/*Student student = new Student();
		student.setName("admin");
		System.out.println(student);*/
		/*
		 * Spring来管理,这里需要创建一个Spring容器,他会扫描整个applicationContext.xml文件,
		 * 当里面出现类时,会直接创建一个对象
		 */
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		//spring容器获取bean的唯一标识符
		Student student = (Student)ctx.getBean("stu");
		//student.setName("admin");
		System.out.println(student);
	}
}
