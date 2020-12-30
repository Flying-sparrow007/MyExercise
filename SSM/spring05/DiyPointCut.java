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
 * 切面
 * @Aspect 标注这个类是一个切面
 * @author LENOVO
 *
 */
@Aspect
@Component
public class DiyPointCut {
	//前置通知
	@Before("execution(* com.gk.spring05.UserServiceImpl.*(..))")
	public void before(){
		/*String str = "";
		str.charAt(2);*/
		System.out.println("***方法前执行!***");
	}
	
	//后置通知
	@After("execution(* com.gk.spring05.UserServiceImpl.*(..))")
	public void after(){
		System.out.println("***方法后执行!***");
	}
	
	/*
	 * 环绕通知:在环绕通知增强中,我们可以给定一个参数,
	 * 代表我们要获取处理切入的点
	 */
	@Around("execution(* com.gk.spring05.UserServiceImpl.*(..))")
	public void around(ProceedingJoinPoint jp) throws Throwable{
		System.out.println("环绕前");
		jp.proceed();//执行方法
		System.out.println("环绕后");
	}
	
	@AfterThrowing("execution(* com.gk.spring05.UserServiceImpl.*(..))")
	public void exception(){
		System.out.println("发生异常了!");
	}
	
	public void log(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d = sdf.format(date);
		System.out.println("在 " + d + " 的时候执行了");
	}
	
}
