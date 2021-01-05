package com.gk.gkJavaSpring_ssm.login.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gk.gkJavaSpring_ssm.login.service.UserNameNotExistException;
import com.gk.gkJavaSpring_ssm.login.service.UserPwdNotSameException;

public abstract class HandlerExceptionMessage {
	
	//�����쳣��Ϣ
	@ExceptionHandler
	public String exceptionHandler(Exception e, HttpServletRequest request){
		if(e instanceof UserNameNotExistException){//�û�������
			//���쳣��Ϣ  e.getMessage()��ȡ�쳣��Ϣ
			request.setAttribute("message", e.getMessage());
			return "login";
		}else if(e instanceof UserPwdNotSameException){//�������
			request.setAttribute("message2", e.getMessage());
			return "login";
		}
		
		return "";
	}
	
}
