package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;

public class ActionServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//���������ַ���
		req.setCharacterEncoding("utf-8");
		//������Ӧ���ͺ���Ӧ�ַ���
		res.setContentType("text/html; charset = utf-8");		
		String uri = req.getRequestURI();
		//System.out.println(uri);
		
		if(uri.endsWith("register.do")){
			registerServlet(req, res);
		}else if(uri.endsWith("list.do")){
			selectUserList(req, res);
		}else if(uri.endsWith("login.do")){
			loginServlet(req, res);
		}else if(uri.endsWith("delete.do")){
			deleteUserByIdServlet(req, res);
		}else if(uri.endsWith("select.do")){
			selectUserByIdServlet(req, res);
		}else if(uri.endsWith("update.do")){
			updateUserByIdServlet(req, res);
		}else if(uri.endsWith("upPage.do")){
			upPageUserServlet(req, res);
		}else if(uri.endsWith("downPage.do")){
			downPageUserServlet(req, res);
		}else if(uri.endsWith("toPage.do")){
			toPageUserServlet(req, res);
		}
	}

	/**
	 * ��ѯָ��ҳ������
	 * @param req
	 * @param res
	 * @throws IOException 
	 */
	private void toPageUserServlet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		//��ȡpage����
		Integer page = Integer.parseInt(req.getParameter("page"));
		
		//����map����������װ����
		 Map<String, Object> map = new HashMap<String, Object>();
		 
		 //��ȡList���Ϸ�װ����
		 List<User> list = new ArrayList<User>();
		 
		 UserDao dao = new UserDaoImpl();
		 
		 //��ȡ��ҳ��count
		 int pageCount = dao.selectPageCountByUserCount();
		 
		 //��ȡsession����
		 HttpSession session = req.getSession();
		 
		 //System.out.println(page);
		 
		 if(page < 1){
			 //ҳ��С��1,ֱ������ҳ��Ϊ��һҳ
			 page = 1;
			 list = dao.selectUserByPage(page);
		 }else if(page > pageCount){
			 //�������һҳ,����ҳ��Ϊ���һҳ
			 page = pageCount;
			 list = dao.selectUserByPage(page);
		 }else{
			 list = dao.selectUserByPage(page);
		 }
		 
		 //System.out.println(list);
		 //System.out.println(page);
		 
		 map.put("list", list);
		 map.put("page", page);
		 session.setAttribute("map", map);
		 
		 res.sendRedirect(req.getContextPath() + "/list.do");
		 
	}

	/**
	 * ��ѯ��һҳ����
	 * @param req
	 * @param res
	 * @throws IOException 
	 */
	private void downPageUserServlet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		//����map����������װ����
		Map<String, Object> map = new HashMap<String, Object>();
		
		//�洢�û�����
		List<User> list = new ArrayList<User>();
		
		//��ȡsession����
		HttpSession session = req.getSession();
		
		//��ȡ��ǰҳ��
		map = (Map<String, Object>)session.getAttribute("map");
		Integer page = (Integer)map.get("page");
		//System.out.println(page);
		
		//��ȡ������
		UserDao dao = new UserDaoImpl();
		//��ȡ��ҳ��
		Integer pageCount = dao.selectPageCountByUserCount();
		if(page >= pageCount){
			//���һҳ�ٴε����һҳ��Ϊ��һҳ
			page = pageCount;
			list = dao.selectUserByPage(page);
		}else{
			//����������һҳ
			page++;
			list = dao.selectUserByPage(page);
		}
		
		//��װ���ݲ������ض���
		map.put("list", list);
		map.put("page", page);
		map.put("pageCount", pageCount);
		session.setAttribute("map", map);
		
		res.sendRedirect(req.getContextPath() + "/list.do");
	}

	/**
	 * ��ѯ��һҳ�û�����
	 * @param req
	 * @param res
	 * @throws IOException 
	 */
	private void upPageUserServlet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		//����map����������װ����
		Map<String, Object> map = new HashMap<String, Object>();
		
		//�洢�û�����
		List<User> list = new ArrayList<User>();
		
		//��ȡsession����
		HttpSession session = req.getSession();
		//��ȡ��ǰҳ��
		map = (Map<String, Object>)session.getAttribute("map");
		Integer page = (Integer)map.get("page");
		//System.out.println(page);
		
		//��ȡ������
		UserDao dao = new UserDaoImpl();
		//��ҳ��
		int pageCount = dao.selectPageCountByUserCount();
		
		if(page < 2){
			//��һҳ������������һҳ�Ļ����������һҳ
			page = 1;
			//��ѯ��ǰҳ���û�����
			list = dao.selectUserByPage(page);
		}else{
			page--;//ҳ����һ
			list = dao.selectUserByPage(page);
		}
		
		//��װ����
		map.put("list", list);
		map.put("page", page);
		map.put("pageCount", pageCount);
		session.setAttribute("map", map);
		
		res.sendRedirect(req.getContextPath() + "/list.do");
	}

	/**
	 * �����û�ID�޸��û�����
	 * @param req
	 * @param res
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void updateUserByIdServlet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		//��ȡ�û�����
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("uname");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		
		//�������ض����ԭ����,��Ҫ���ݴ�����Ϣ
		HttpSession session = req.getSession();
		
		if(name == null || name.trim() == ""){
			session.setAttribute("nameMeg", "�û�������Ϊ��!");
			res.sendRedirect(req.getContextPath() + "/select.do?id=" + id);
			return ;
		}
		if(name.contains(" ")){
			session.setAttribute("nameMeg", "�û������ܰ����ո�!");
			res.sendRedirect(req.getRequestURI() + "/select.do?id=" + id);
			return ;
		}
		
		UserDao dao = new UserDaoImpl();
		
		//�û��������ظ�,�����û�������Ϊ�Լ�ԭ��������
		String oldName = dao.selectUserById(id).getUserName();
		if(!oldName.equals(name) && dao.selectUserByName(name)){
			session.setAttribute("nameMeg", "�û����Ѵ���!");
			res.sendRedirect(req.getContextPath() + "/select.do?id=" + id);
			return ;
		}
		
		//��װ�û�����
		User user = new User();
		user.setId(id);
		user.setUserName(name);
		user.setUserEmail(email);
		user.setUserPhone(phone);
		
		//�޸��û�����
		int n = dao.updateUserMegById(user);
		if(n > 0){
			//�޸ĳɹ���ת���û��б�ҳ��
			res.sendRedirect(req.getContextPath() + "/list.do");
			//��ת���µ�ҳ��֮����Ҫ�Ƴ�updateUser�������Ϣ
			session.removeAttribute("updateUser");
		}else{
			/*
			 * �޸�ʧ����ת������ID�����û�ҳ��,����ID�����û�ҳ���Զ���ת���޸�ҳ��
			 * ��Ϊ��Ҫ�ȸ����û�id���ҵ��û���Ϣ,���ܹ��޸��û�����
			 */
			session.setAttribute("updateFailure", "�޸�ʧ��,����ϵ������Ա!");
			res.sendRedirect(req.getContextPath() + "/select.do?id=" + id);
		}
	}

	/**
	 * ����ID�����û�����
	 * @param req
	 * @param res
	 * @throws IOException 
	 */
	private void selectUserByIdServlet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		UserDao dao = new UserDaoImpl();
		User user = dao.selectUserById(id);
		HttpSession session = req.getSession();
		if(user != null){
			session.setAttribute("updateUser", user);
			res.sendRedirect(req.getContextPath() + "/update.jsp");
		}else{
			session.setAttribute("failure", "�޸�ʧ��,�������޸�!");
			/*
			 * �ض���list.do����Ϊ,listUsers.jsp�������Ϣ��ͨ��list.do����ȥ��,
			 * ֱ�ӷ���listUsers.jsp�ᵼ�������޷����
			 */
			res.sendRedirect(req.getContextPath() + "/list.do");
		}
	}

	/**
	 * ����IDɾ���û�
	 * @param req
	 * @param res
	 * @throws IOException 
	 */
	private void deleteUserByIdServlet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		//��ȡID
		Integer id = Integer.parseInt(req.getParameter("id"));
		UserDao dao = new UserDaoImpl();
		dao.deleteUserById(id);
		res.sendRedirect(req.getContextPath() + "/list.do");
	}

	/**
	 * �����û���¼����
	 * @param req
	 * @param res
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void loginServlet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//��ȡ����
		String username = req.getParameter("uname");
		String password = req.getParameter("pwd");
		
		UserDao dao = new UserDaoImpl();
		if(!dao.selectUserByName(username)){
			req.setAttribute("nameMeg", "�û���������!");
			req.getRequestDispatcher("login.jsp").forward(req, res);
			return ;
		}
		
		/*
		 * Ϊ�û��������,��Ϊ���ݿ�����洢�����Ѽ��ܹ�������
		 * �����ж������Ƿ���ȷ��Ҫ�ȼ�����ƥ���Ƿ���ȷ
		 */
		String salt = "�ɰ�������";
		password = DigestUtils.md5Hex(password + salt);
		
		User user = dao.loginByNameAndPwd(username, password);
		if(user == null){
			req.setAttribute("pwdMeg", "�û��������!");
			req.getRequestDispatcher("login.jsp").forward(req, res);
			return ;
		}
		
		HttpSession session = req.getSession();
		session.setAttribute("userLogined", user);
		res.sendRedirect(req.getContextPath() + "/list.do");
	}

	/**
	 * ��ѯ�û��б���
	 * @throws IOException 
	 * @throws ServletException
	 */
	private void selectUserList(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		UserDao dao = new UserDaoImpl();
		HttpSession session = req.getSession();
		
		Map<String, Object> map = new HashMap<>();
		if(session.getAttribute("map") == null){
			//System.out.println("�Ǻ�");
			
			//Ĭ��Ϊ��һҳ
			int page = 1;
			List<User> list = new ArrayList<User>();
			list = dao.selectUserByPage(page);
			
			//��װ����
			map.put("list", list);
			map.put("page", page);
			int pageCount = dao.selectPageCountByUserCount();//��ѯ��ҳ��
			map.put("pageCount", pageCount);
			//������
			session.setAttribute("map", map);
		}
		
		req.getRequestDispatcher("listUsers.jsp").forward(req, res);
		/*
		 * ���session��������update.do����������ӵĴ�����ʾ
		 * ��Ϊsession���������ڱȽϳ�,����Ϊ�˲�Ӱ������޸Ĺ���,
		 * ��Ҫ���޸���ɲ���ʾ�û��б�֮�����������
		 */
		req.getSession().removeAttribute("nameMeg");
	}

	/**
	 * �û�ע�Ṧ��
	 * @throws IOException 
	 * @throws ServletException
	 */
	private void registerServlet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		//��ȡ�û�����
		String username = req.getParameter("uname");
		String password = req.getParameter("pwd");
		String userPhone = req.getParameter("phone");
		String userEmail = req.getParameter("email");
		
		if(username == null || username.trim() == ""){
			req.setAttribute("nameMeg", "�û�������Ϊ��!");
			req.getRequestDispatcher("addUser.jsp").forward(req, res);
			return ;
		}
		
		if(username.contains(" ")){
			req.setAttribute("nameMeg", "�û������ܰ����ո�!");
			req.getRequestDispatcher("addUser.jsp").forward(req, res);
			return ;
		}
		
		UserDao dao = new UserDaoImpl();
		
		if(dao.selectUserByName(username)){
			req.setAttribute("nameMeg", "�û����Ѵ���!");
			req.getRequestDispatcher("addUser.jsp").forward(req, res);
			return ;
		}
		
		//System.out.println(password);
		if(password == null || password.trim() == ""){
			req.setAttribute("pwdMeg", "�û����벻��Ϊ��");
			req.getRequestDispatcher("addUser.jsp").forward(req, res);
			return ;
		}
		
		if(password.contains(" ")){
			req.setAttribute("pwdMeg", "�û����벻�ܺ��пո�");
			req.getRequestDispatcher("addUser.jsp").forward(req, res);
			return ;
		}
		
		//��
		String salt = "�ɰ�������";
		password = DigestUtils.md5Hex(password + salt);
		
		//��װ�û�����
		User user = new User();
		user.setUserName(username);
		user.setPassword(password);
		user.setUserEmail(userEmail);
		user.setUserPhone(userPhone);
		
		int n = dao.register(user);
		if(n > 0){
			//ע��ɹ�
			res.sendRedirect("login.jsp");
		}else{
			//ע��ʧ��
			req.setAttribute("failure", "ע��ʧ��,������ע��!");
			req.getRequestDispatcher("addUser.jsp").forward(req, res);
			return ;
		}
	}
	
	
}
