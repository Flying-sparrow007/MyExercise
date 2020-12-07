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
		 * ServletRequest��HttpServletRequest�ĸ���
		 * ServletResponse��HttpServletResponse�ĸ���
		 * ��Ϊ������еĹ��ܱȸ����,����������Ҫǿ������ת��
		 */
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		//��ȡsession����
		HttpSession session = req.getSession();
		//��ȡ������Դ��ַ
		String uri = req.getRequestURI();
		
		//����
		if(uri.endsWith("login.do") || uri.endsWith("register.do")){
			//��¼��ע�᲻��Ҫ����
			chain.doFilter(req, res);
			return ;
		}
		
		User user = (User)session.getAttribute("userLogined");
		if(user == null){
			//�û�δ��¼
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
