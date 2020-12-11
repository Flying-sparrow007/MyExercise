package com.gk.spring03;

import java.util.Calendar;

import org.junit.Test;

/**
 * 静态工厂模式:
 * 用类去调用静态方法返回一个实例,称之为静态工厂模式(单例模式)
 * @author LENOVO
 *
 */
public class FactoryDemo {
	@Test
	public void test(){
		Calendar c = Calendar.getInstance();
	}
}
