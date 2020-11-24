package web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SomeServlet extends HttpServlet {
	//1.servlet������
	public SomeServlet(){
		System.out.println("SomeServlet�Ĺ�����");
	}
	
	//3.����service
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("SomeServlet��service����");
	}
	
	/*
	 * 2.ͨ��Override GenericServlet��init����,��ʵ���Լ���
	 * ��ʼ�������߼�
	 */
	@Override
	public void init() {
		System.out.println("SomeServlet�ĳ�ʼ��");
		//��ȡ�ֲ���ʼ������
		String name = getInitParameter("username");
		System.out.println("��ȡ���ĳ�ʼ��������:" + name);
	}
	
	/*
	 * 4.�رշ�������������
	 * ͨ��Override GenericServlet��destroy����,��ʵ���Լ���
	 */
	@Override
	public void destroy() {
		System.out.println("SomeServlet�����ٷ���");
	}
	
	//��ȡȫ�ֳ�ʼ������
	@Override
	public void init(ServletConfig config) throws ServletException {
		String name = config.getServletContext().getInitParameter("username");
		System.out.println(name);
	}
}
