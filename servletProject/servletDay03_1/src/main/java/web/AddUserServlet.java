package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;

public class AddUserServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//���ǰ������ʽpost��Ч
			request.setCharacterEncoding("UTF-8");
			//������Ӧҳ���ַ���
			response.setContentType("text/html; charset = UTF-8");
			
			//��ȡ�û���Ϣ
			String userName = request.getParameter("username");
			String userPwd = request.getParameter("password");
			String userEmail = request.getParameter("email");
			String userPhone = request.getParameter("phone");
			
			//�û�������Ϊ��
			if(userName == null || userName.trim().isEmpty()){
				request.setAttribute("meg", "�û�������Ϊ��!");
				//��ȡת������ת��
				request.getRequestDispatcher("addUser.jsp").forward(request, response);
				return ;
			}
			
			//�û������ܰ����ո�
			if(userName.contains(" ")){
				request.setAttribute("meg", "�û������ܰ����ո�!");
				request.getRequestDispatcher("addUser.jsp").forward(request, response);
				return ;
			}
			
			//�û�����ռ��
			UserDao dao = new UserDaoImpl();
			int count = dao.findUserByName(userName);
			if(count > 0){
				request.setAttribute("meg", "�û�����ռ��!");
				request.getRequestDispatcher("addUser.jsp").forward(request, response);
				return ;
			}
			
			//��
			String str = "�ɰ�������";
			
			//�ļ�ժҪ���Ҹ�ԭʼ�������
			String md5 = DigestUtils.md5Hex(userPwd + str);
			
			//���û����ݷ�װ��User������
			User user = new User();
			user.setUserName(userName);
			user.setUserPwd(md5);
			user.setUserEmail(userEmail);
			user.setUserPhone(userPhone);
			
			//��User������뵽���ݿ���
			dao.addUser(user);	
			//��ȡ������
			PrintWriter pw = response.getWriter();
			pw.println("��ӳɹ�");
		} catch (Exception e) {
			request.setAttribute("message", "ϵͳ��æ,���Ժ�����...");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	}
}
