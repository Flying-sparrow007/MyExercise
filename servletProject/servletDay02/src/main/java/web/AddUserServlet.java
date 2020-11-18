package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;

public class AddUserServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���post��Ч
		request.setCharacterEncoding("UTF-8");
		//������Ӧҳ���ַ���
		response.setContentType("text/html; charset = UTF-8");
		
		//��ȡ�û���Ϣ
		String userName = request.getParameter("username");
		String userPwd = request.getParameter("password");
		String userEmail = request.getParameter("email");
		String userPhone = request.getParameter("phone");
		
		//���û����ݷ�װ��User������
		User user = new User();
		user.setUserName(userName);
		user.setUserPwd(userPwd);
		user.setUserEmail(userEmail);
		user.setUserPhone(userPhone);
		
		//��User������뵽���ݿ���
		UserDao dao = new UserDaoImpl();
		int n = dao.addUser(user);
		if(n < 1){
			throw new RuntimeException("ϵͳά��,���Ժ�����...");
		}		
		//��ȡ������
		PrintWriter pw = response.getWriter();
		pw.println("��ӳɹ�");
	}
}
