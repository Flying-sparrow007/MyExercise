package com.gk.spring02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test01 {
	public static void main(String[] args) {
		/*Student student = new Student();
		student.setName("admin");
		System.out.println(student);*/
		/*
		 * Spring������,������Ҫ����һ��Spring����,����ɨ������applicationContext.xml�ļ�,
		 * �����������ʱ,��ֱ�Ӵ���һ������
		 */
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		//spring������ȡbean��Ψһ��ʶ��
		Student student = (Student)ctx.getBean("stu");
		//student.setName("admin");
		System.out.println(student);
	}
}
