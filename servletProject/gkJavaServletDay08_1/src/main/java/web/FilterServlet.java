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

import entity.User;

public class FilterServlet implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//1.在这里我们真正要调用的是子接口中的生命方法,所以要强转类型
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		//2.如果是用户登录路径或用户注册路径则直接通过
		String uri = req.getRequestURI();
		if(uri.endsWith("login.do") || uri.endsWith("add.do")){
			chain.doFilter(req, res);
			return ;
		}
		
		//3.过滤所有请求路径
		User user = (User)req.getSession().getAttribute("user");
		if(user == null){//不能通过,重定向到登录页面
			//重定向到登录页面(最好使用绝对路径)
			res.sendRedirect(req.getContextPath() + "/login.jsp");
		}else{//通过
			chain.doFilter(req, res);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
