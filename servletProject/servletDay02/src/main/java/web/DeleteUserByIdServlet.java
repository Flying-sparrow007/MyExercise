package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.UserDaoImpl;

public class DeleteUserByIdServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//������Ӧ����
		response.setContentType("text/html; charset = utf-8");
		PrintWriter pw = response.getWriter();
		
		//��ȡ�������
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		//���ݲ���ɾ��ID
		UserDao dao = new UserDaoImpl();
		int n = dao.deleteUserById(id);
		if(n > 0){
			pw.println("ɾ���ɹ�!");
		}else{
			throw new RuntimeException("ϵͳά��,���Ժ�����...");
		}
		
	}
}
