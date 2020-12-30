package com.gk.spring05;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * ����
 * @Aspect ��ע�������һ������
 * @author LENOVO
 *
 */
@Aspect
@Component
public class DiyPointCut {
	//ǰ��֪ͨ
	@Before("execution(* com.gk.spring05.UserServiceImpl.*(..))")
	public void before(){
		/*String str = "";
		str.charAt(2);*/
		System.out.println("***����ǰִ��!***");
	}
	
	//����֪ͨ
	@After("execution(* com.gk.spring05.UserServiceImpl.*(..))")
	public void after(){
		System.out.println("***������ִ��!***");
	}
	
	/*
	 * ����֪ͨ:�ڻ���֪ͨ��ǿ��,���ǿ��Ը���һ������,
	 * ��������Ҫ��ȡ��������ĵ�
	 */
	@Around("execution(* com.gk.spring05.UserServiceImpl.*(..))")
	public void around(ProceedingJoinPoint jp) throws Throwable{
		System.out.println("����ǰ");
		jp.proceed();//ִ�з���
		System.out.println("���ƺ�");
	}
	
	@AfterThrowing("execution(* com.gk.spring05.UserServiceImpl.*(..))")
	public void exception(){
		System.out.println("�����쳣��!");
	}
	
	public void log(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d = sdf.format(date);
		System.out.println("�� " + d + " ��ʱ��ִ����");
	}
	
}
