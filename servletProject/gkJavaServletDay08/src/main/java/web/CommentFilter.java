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

public class CommentFilter implements Filter {

	//��������֮��,�����̽�������ʵ����,ֻ�ᴴ��һ��
	public CommentFilter() {
		System.out.println("����CommentFilter�Ĺ�����!");
	}
	
	/*
	 * ʵ����֮�����������̵���init������ɳ�ʼ������.
	 * 1.�����ὫFilterConfig������Ϊ�������ݹ�����
	 * 2.FilterConfig�����ṩ�˶�ȡ��ʼ�������ķ���(getInitParameter)
	 * 3.init()����ִֻ��һ��
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//String name = filterConfig.getInitParameter("username");//��ȡ�ֲ�����
		String name = filterConfig.getServletContext().getInitParameter("username");//��ȡȫ�ֲ���
		System.out.println("����CommentFilter�ĳ�ʼ����,���ĳ�ʼ��������:" + name);
	}

	/*
	 * �����յ�����֮��,�����doFilter����,
	 * ����������(�൱��service����),�����Ὣrequest�����response����
	 * ��Ϊ�������ݹ���
	 * a.ServletRequest��HttpServletRequest�ĸ��ӿ�
	 * b.ServletResponse��HttpServletResponse�ĸ��ӿ�
	 * c.���������FilterChain(��������)��doFilter����,��������������,
	 * �����ж�����,���ش�����
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		/*
		 * (�˽�)��ΪSun�������,��������������Ҫ���õ����ӽӿ��е���������
		 */
		System.out.println("Filter��ʼ");
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		//���������ַ���
		req.setCharacterEncoding("utf-8");
		//������Ӧ�ַ���
		res.setContentType("text/html; charset = utf-8");
		//��ȡ�������
		String content = req.getParameter("content");
		//�������дʻ����
		if(content.contains("�Բ�")){
			res.getWriter().println("��ע���ô�!");
		}else{//ͨ��
			chain.doFilter(req, res);
			System.out.println("Filter����!");
		}
		
	}

	@Override
	public void destroy() {
		System.out.println("����CommentFilter�����ٷ���!");
	}

}
