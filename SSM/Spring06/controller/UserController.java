package com.gk.login.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gk.login.entity.User;
import com.gk.login.service.AppException;
import com.gk.login.service.AppPwdException;
import com.gk.login.service.UserService;

/**
 * 控制层
 * @author LENOVO
 *
 */
@Controller
@RequestMapping("user")
public class UserController {
	//注入业务层
	@Resource
	private UserService service;
	
	//登录页面
	@RequestMapping("/toLogin.do")
	public String toLogin(){
		System.out.println("toLogin()");
		return "login";
	}
	
	@RequestMapping("/login.do")
	public String login(User user, HttpServletRequest request, HttpSession session){
		try {
			System.out.println(user.getUserName() + ", " + user.getUserPassword());
			User u = service.checkUserLogin(user);
			session.setAttribute("user", u);
		} catch (Exception e) {
			if(e instanceof AppException){
				//绑定用户名异常信息
				request.setAttribute("message", e.getMessage());
				return "login";
			}
			if(e instanceof AppPwdException){
				//绑定密码异常信息
				request.setAttribute("message2", e.getMessage());
				return "login";
			}
		}
		return "success";
	}
	
	@RequestMapping("/success.do")
	public String success(){
		System.out.println("success()");
		return "success";
	}
}
