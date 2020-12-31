package com.gk.login.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.gk.login.entity.User;

@Controller
@RequestMapping("user")
public class UserController {
	
	//��½ҳ��
	@RequestMapping("/toLogin.do")
	public String toLogin(){
		System.out.println("toLogin()");
		return "login";
	}
	
	@RequestMapping("/login.do")
	public String login(HttpServletRequest req){
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		System.out.println(username + ", " + password);
		System.out.println("login()");
		return "success";
	}
	
	@RequestMapping("/login2.do")
	public String login2(String username, String password){
		System.out.println(username + ", " + password);
		System.out.println("login2()");
		return "success";
	}
	
	@RequestMapping("/login2_2.do")
	public String login2_2(String username, @RequestParam("password")String pwd){
		System.out.println(username + ", " + pwd);
		System.out.println("login2_2()");
		return "success";
	}
	
	@RequestMapping("/login3.do")
	public String login3(User user){
		String username = user.getUsername();
		String password = user.getPassword();
		System.out.println(username + ", " + password);
		System.out.println("login3()");
		return "success";
	}
	
	@RequestMapping("/login4.do")
	public String login4(User user, HttpServletRequest request){
		String userName = user.getUsername();
		request.setAttribute("username", userName);
		//DispatcherServletĬ�ϻ�ת����ĳ��jspҳ��
		return "success";
	}
	
	@RequestMapping("/login5.do")
	public ModelAndView login5(User user){
		String userName = user.getUsername();
		Map<String, String> map = new HashMap<>();
		map.put("username", userName);
		/*
		 * new ModelAndView("success", map)
		 * success: ��ͼ��
		 * map: �󶨵�����
		 * (�˽�)DispatcherServlet����key��Ϊ������������request������
		 */
		ModelAndView mv = new ModelAndView("success", map);
		return mv;
	}
	
	@RequestMapping("/login6.do")
	public String login6(User user, ModelMap map){
		String userName = user.getUsername();
		/*
		 * DispatcherServlet����AddAttribute�İ���("username")��Ϊ����,
		 * �����ݰ󶨵�request������
		 * ModelҲ�������ModelMap,���߲��ֻ�ǽṹ��ͬ,����һ��
		 */
		map.addAttribute("username", userName);
		return "success";
	}
	
	@RequestMapping("/login7.do")
	public String login7(User user, HttpSession session){
		String userName = user.getUsername();
		session.setAttribute("username", userName);
		return "success";
	}
	
	//����String���͵��ض���
	@RequestMapping("/login8.do")
	public String login8(User user){
		String userName = user.getUsername();
		System.out.println("userName: " + userName);
		//�ض���ؼ���"redirect:�����ַ"
		return "redirect:toSuccess.do";
	}
	
	@RequestMapping("/toSuccess.do")
	public String toSuccess(){
		return "success";
	}
	
	//����ModelAndView���͵��ض���(�˽�)
	@RequestMapping("/login9.do")
	public ModelAndView login9(){
		//�����ض�����ͼ����
		RedirectView view = new RedirectView("toSuccess.do");
		ModelAndView model = new ModelAndView(view);
		return model;
	}
}
