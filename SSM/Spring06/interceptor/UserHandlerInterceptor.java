package com.gk.login.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.gk.login.entity.User;

/**
 * 拦截器要实现接口HandlerInterceptor
 * @author LENOVO
 *
 */
@Component
public class UserHandlerInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("session验证开始...");
		//获取session对象
		HttpSession session = request.getSession();
		//通过session对象获取绑定数据
		User user = (User)session.getAttribute("user");
		if(user == null){
			//用户没有登录需要拦截
			//重定向到登录页面
			response.sendRedirect(request.getContextPath() + "/user/toLogin.do");
			return false;
		}
		//用户登录过,允许访问,不拦截
		return true;
	}

	/*
	 * 被拦截请求成功之后在返回页面之前所执行该postHandle方法
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle");
	}

	/*
	 * 被拦截请求返回页面成功后执行该方法
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion");
	}

}
