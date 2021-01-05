package com.gk.gkJavaSpring_ssm.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gk.gkJavaSpring_ssm.login.service.UserService;

/**
 * ���Ʋ�
 * @author LENOVO
 *
 */
@Controller
@RequestMapping("user")
public class UserController extends HandlerExceptionMessage {
	//ע��ҵ���
	@Autowired
	private UserService service;
	
	//��¼ҳ��
	@RequestMapping("/login.do")
	public String loginHtml(){
		return "login";
	}
	
	//
	@RequestMapping("/toUserLogin.do")
	public String toUserLogin(@RequestParam("userName")String userName, @RequestParam("userPassword")String pwd){
		service.userLogin(userName, pwd);
		return "success";
	}
}
