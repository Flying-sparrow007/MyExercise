package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
		//������Ӧ���ͺ��ַ���
		response.setContentType("text/html; charset = utf-8");
		//ͨ��HttpServletResponse��ȡ�����
		PrintWriter pw = response.getWriter();
		
		//��ѯ�����е��û���Ϣ
		UserDao dao = new UserDaoImpl();
		List<User> list = dao.findAllUser();
		if(list == null){
			pw.println("û������!");
		}
		//���ݲ�ѯ�������û���Ϣ,������
		pw.println("<table border = '>' width = '60%' cellpadding = '0'");
		pw.println("<tr><th>�û�ID</th><th>�û���</th><th>����</th><th>�绰</th></tr>");
		for(User user: list){
			pw.println("<tr><td>" + user.getId() + "</td><td>" + user.getUserName() + "</td><td>" + user.getUserEmail() + "</td><td>" + user.getUserPhone() + "</td></tr>");
		}
		pw.println("</table>");
		pw.println("<form action = 'update'>");
		pw.println("<input style = 'margin: 10px 330px; height: 30px; width: 150px; text-align: center; line-height: 30px;' type = 'text' placeholder = '������Ҫ�޸ĵ��û�id' name = 'id'>");
		pw.println("<input style = 'margin: 5px 330px; height: 30px; width: 150px; text-align: center; line-height: 30px;' type = 'text' placeholder = '������Ҫ�޸ĵ��û�����' name = 'password'>");
		pw.println("<input style = 'margin: 5px 370px; height: 30px; width: 50px; text-align: center; line-height: 30px;' type = 'submit' value = '�ύ'>");
		pw.println("</form>");
		
	}
}
