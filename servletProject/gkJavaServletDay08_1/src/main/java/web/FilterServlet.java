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
		//1.��������������Ҫ���õ����ӽӿ��е���������,����Ҫǿת����
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		//2.������û���¼·�����û�ע��·����ֱ��ͨ��
		String uri = req.getRequestURI();
		if(uri.endsWith("login.do") || uri.endsWith("add.do")){
			chain.doFilter(req, res);
			return ;
		}
		
		//3.������������·��
		User user = (User)req.getSession().getAttribute("user");
		if(user == null){//����ͨ��,�ض��򵽵�¼ҳ��
			//�ض��򵽵�¼ҳ��(���ʹ�þ���·��)
			res.sendRedirect(req.getContextPath() + "/login.jsp");
		}else{//ͨ��
			chain.doFilter(req, res);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
