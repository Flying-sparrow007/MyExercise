package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;

public class FindUserByIdServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//������Ӧ
		response.setContentType("text/html; charset = utf-8");
		
		//��ȡ����,��ȡ�����ַ���,�����Ҫǿת��Integer����
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		//���ô���UserDao����,����UserDao�ӿ��еķ���
		UserDao dao = new UserDaoImpl();
		User user = dao.findUserById(id);
		//��user����
		request.setAttribute("user", user);
		//��ȡת������ת��updateUser.jsp
		request.getRequestDispatcher("updateUser.jsp").forward(request, response);
	}
}
