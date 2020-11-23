package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;

public class ActionServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ������Դ����·��
		//System.out.println(request.getContextPath());
		//��ȡ������Դ·��
		//System.out.println(request.getRequestURI());
		//��ȡ������Դ����·��
		//System.out.println(request.getRequestURL());
		//��ȡ��ʵ·��(�Ǵӵ�ǰ��servlet��tomact�еĴ���ļ��п�ʼ)
		//System.out.println(request.getServletContext().getRealPath("/"));
		
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		/*uri = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		if("/add".equals(uri)){
			//�������ҵ��
			System.out.println("�������ҵ��!");
		}else if("/find".equals(uri)){
			//�����ѯҵ��
			System.out.println("�����ѯҵ��!");
		}*/
		
		//�����������ݴ���ͬ��ҵ��
		if(uri.endsWith("add.do")){
			//System.out.println("���ҵ��!");
			//����û�ҵ��
			addUserServletAction(request, response);
		}else if(uri.endsWith("list.do")){
			//System.out.println("��ѯҵ��!");
			//��ѯ�û��б�ҵ��
			findUserServletAction(request, response);
		} else if(uri.endsWith("delete.do")){
			//ɾ���û�����ҵ��
			deleteUserByIdServletAction(request, response);
		}else if(uri.endsWith("login.do")){
			//�û���¼ҵ��
			loginUserServletAction(request, response);
		}else if(uri.endsWith("userById.do")){
			//�����û�id��ѯ�û�����ҵ��
			findUserByIdServletAction(request, response);
		}else if(uri.endsWith("update.do")){
			//�޸��û�����ҵ��
			updateUserServletAction(request, response);
		}else{
			
		}
		
	}

	//�޸��û�����ҵ��
	private void updateUserServletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		request.getRequestDispatcher("list.do").forward(request, response);
	}

	//�����û�ID��ѯ�û�����ҵ��
	private void findUserByIdServletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

	//��½ҵ��
	private void loginUserServletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			request.getRequestDispatcher("list.do").forward(request, response);
			
		} catch (Exception e) {
			request.setAttribute("message", "ϵͳ��æ,���Ժ�����...");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	//ɾ���û�����ҵ��
	private void deleteUserByIdServletAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//������Ӧ����
				response.setContentType("text/html; charset = utf-8");
				PrintWriter pw = response.getWriter();
				
				//��ȡ�������
				Integer id = Integer.parseInt(request.getParameter("id"));
				
				//���ݲ���ɾ��ID
				UserDao dao = new UserDaoImpl();
				int n = dao.deleteUserById(id);
				if(n < 1){
					throw new RuntimeException("ϵͳά��,���Ժ�����...");
				}
				//pw.println("ɾ���ɹ�!");
				//ɾ���ɹ����ض���list
				response.sendRedirect("list.do");
	}

	//��ѯ�û�ҵ��
	private void findUserServletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			//System.out.println("hello");
		} catch (Exception e) {
			request.setAttribute("message", "ϵͳ��æ,���Ժ�����...");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	//����û�ҵ��
	private void addUserServletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			/*PrintWriter pw = response.getWriter();
			pw.println("��ӳɹ�");*/
			
			//�ض��򵽵�¼ҳ��
			/*
			 * HttpServletResponse�ṩ���ض��򷽷� sendRedirect(String location)
			 */
			response.sendRedirect("login.jsp");
			
		} catch (Exception e) {
			request.setAttribute("message", "ϵͳ��æ,���Ժ�����...");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
}
