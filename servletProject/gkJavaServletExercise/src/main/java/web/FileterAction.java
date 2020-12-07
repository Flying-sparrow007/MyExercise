package web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.User;

public class FileterAction implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		/*
		 * ServletRequest是HttpServletRequest的父类
		 * ServletResponse是HttpServletResponse的父类
		 * 因为子类具有的功能比父类多,所以这里需要强制类型转换
		 */
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		//获取session对象
		HttpSession session = req.getSession();
		//获取请求资源地址
		String uri = req.getRequestURI();
		
		//过滤
		if(uri.endsWith("login.do") || uri.endsWith("register.do")){
			//登录和注册不需要过滤
			chain.doFilter(req, res);
			return ;
		}
		
		User user = (User)session.getAttribute("userLogined");
		if(user == null){
			//用户未登录
			res.sendRedirect("login.jsp");
			return ;
		}else{
			chain.doFilter(req, res);
			return ;
		}
		
	}

	@Override
	public void destroy() {
		
	}

}
