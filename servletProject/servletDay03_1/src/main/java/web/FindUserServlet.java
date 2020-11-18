package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;

public class FindUserServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//������Ӧ���ͺ��ַ���
			response.setContentType("text/html; charset = utf-8");
			//ͨ��HttpServletResponse��ȡ�����
			PrintWriter pw = response.getWriter();
			
			//��ѯ�����е��û���Ϣ
			UserDao dao = new UserDaoImpl();
			List<User> list = dao.findAllUser();
			
			//��request������
			request.setAttribute("list", list);
			
			//��ȡrequest�����󶨵�����
			/*
			 * Object obj = request.getAttribute("list");//���ص���Object����
			 * List<User> listUser = (List<User>)obj;
			 */
			
			
			//ת��
			RequestDispatcher rd = request.getRequestDispatcher("listUsers.jsp");
			rd.forward(request, response);
			
			//ת��֮�����Ĵ��뻹��ִ��
			System.out.println("hello");
		} catch (Exception e) {
			request.setAttribute("message", "ϵͳ��æ,���Ժ�����...");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	}
}
