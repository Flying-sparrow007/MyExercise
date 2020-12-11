package com.gk.spring03;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class FactoryDemo02 {
	@Test
	public void test01(){
		Calendar c = Calendar.getInstance();
		Date date = c.getTime();
		System.out.println(date);
	}
}
