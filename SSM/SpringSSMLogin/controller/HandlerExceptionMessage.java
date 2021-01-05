package com.gk.gkJavaSpring_ssm.login.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gk.gkJavaSpring_ssm.login.service.UserNameNotExistException;
import com.gk.gkJavaSpring_ssm.login.service.UserPwdNotSameException;

public abstract class HandlerExceptionMessage {
	
	//捕获异常信息
	@ExceptionHandler
	public String exceptionHandler(Exception e, HttpServletRequest request){
		if(e instanceof UserNameNotExistException){//用户不存在
			//绑定异常信息  e.getMessage()获取异常信息
			request.setAttribute("message", e.getMessage());
			return "login";
		}else if(e instanceof UserPwdNotSameException){//密码错误
			request.setAttribute("message2", e.getMessage());
			return "login";
		}
		
		return "";
	}
	
}
