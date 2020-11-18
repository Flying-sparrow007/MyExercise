package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet�����յ�����֮��,�����servlet��service��������������
 * ע:������ʵ�ִ�����request��response,����,request�����װ���������ݰ�
 * �������������,���ǿ��Խ�������д��response��������,Ȼ�����������response����
 * ��ȡ������,Ȼ�������͸������
 * @author LENOVO
 *
 */
public class ServletHello extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����content-type��Ϣͷ,������������ص���������
		response.setContentType("text/html");
		//ͨ��response�����ȡ�ַ������
		PrintWriter pw = response.getWriter();
		/*
		 * ������д��response������,servlet�����Ὣresponse�����ϴ�ŵ�����
		 * ȡ����,��һ����(��Ӧ���ݰ�),Ȼ���͸������
		 */
		pw.println("<h1>Hello World</h1>");
		//���û�е���close(0����,���������Զ��ر���
		pw.close();
	}
}
