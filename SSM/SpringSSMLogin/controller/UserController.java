package com.gk.gkJavaSpring_ssm.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gk.gkJavaSpring_ssm.login.service.UserService;

/**
 * 控制层
 * @author LENOVO
 *
 */
@Controller
@RequestMapping("user")
public class UserController extends HandlerExceptionMessage {
	//注入业务层
	@Autowired
	private UserService service;
	
	//登录页面
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
