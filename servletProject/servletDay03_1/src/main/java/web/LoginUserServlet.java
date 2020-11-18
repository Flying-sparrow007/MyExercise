package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

import dao.UserDao;
import dao.UserDaoImpl;

public class LoginUserServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//���������ַ���
			request.setCharacterEncoding("utf-8");
			//������Ӧ���ͺ��ַ���
			response.setContentType("text/html; charset = utf-8");
			
			String userName = request.getParameter("username");
			String userPwd = request.getParameter("password");
			//System.out.println(userName + "," + userPwd);
			
			//�ж��û��������Ƿ�Ϸ�
			if(userName == null || userName.trim().isEmpty() || userName.contains(" ")){
				request.setAttribute("megName", "�û�����������,����������!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return ;
			}
			
			//�ж��û����Ƿ����
			UserDao dao = new UserDaoImpl();
			int count = dao.findUserByName(userName);
			if(count < 1){
				request.setAttribute("megName", "�û���������,����������!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return ;
			}
			
			//��
			String str = "�ɰ�������";
			
			//�ļ�ժҪ����ԭʼ�������
			String md5 = DigestUtils.md5Hex(userPwd + str);//�û����������(�Ѽ���)
			System.out.println(md5);
			
			String password = dao.findUserPwdByName(userName);//���ݿ��ѯ������(�Ѽ���)
			//�ж������Ƿ���ȷ
			if(!password.equals(md5)){
				request.setAttribute("megPwd", "�������,����������!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return ;
			}
			
			//��½�ɹ�(ת�����û��б�ҳ��)
			request.getRequestDispatcher("list").forward(request, response);
			
		} catch (Exception e) {
			request.setAttribute("message", "ϵͳ��æ,���Ժ�����...");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	}
}
