package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;

public class UpdateUserServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���������ַ���
		request.setCharacterEncoding("utf-8");
		
		//��ȡ����
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("username");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		
		//���û����ݷ�װ��user������
		User user = new User();
		user.setId(id);
		user.setUserName(name);
		user.setUserPhone(phone);
		user.setUserEmail(email);
		
		
		//�޸��û�����
		UserDao dao = new UserDaoImpl();
		int n = dao.updateUserData(user);
		if(n < 1){
			request.setAttribute("message", "ϵͳά��,���Ժ�����...");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
		//ת��list
		request.getRequestDispatcher("list").forward(request, response);
	}
}
