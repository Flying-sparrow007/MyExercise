package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * request�ṩ��setCharacterEncoding(String charset)
		 * �÷������post������Ч
		 */
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("username");
		String pwd = request.getParameter("password");
		
		System.out.println(name + "," + pwd);
		
		String state = "";
		if("admin".equals(name) && "123456".equals(pwd)){
			state = "�ɹ�!";
		}else{
			state = "ʧ��!";
		}
		
		/*response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");*/
		response.setContentType("text/html;charset = utf-8");
		PrintWriter pw = response.getWriter();
		
		pw.println(state);
	}
}
