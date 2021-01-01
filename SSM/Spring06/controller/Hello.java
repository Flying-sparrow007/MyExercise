package com.gk.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Hello {
	
	@ExceptionHandler
	public String handler(Exception e){
		if(e instanceof NumberFormatException){
			return "error1";
		}else if(e instanceof StringIndexOutOfBoundsException){
			return "error2";
		}
		return null;
	}
	
	@RequestMapping("/hello.do")
	public String hello(){
		System.out.println(Integer.parseInt("ad11"));
		return null;
	}
	
	@RequestMapping("/hello2.do")
	public String hello2(){
		String str = "";
		str.charAt(2);
		return null;
	}
}
