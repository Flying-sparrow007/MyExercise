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
		//设置请求字符集
		req.setCharacterEncoding("utf-8");
		//设置响应类型和响应字符集
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
	 * 查询指定页的数据
	 * @param req
	 * @param res
	 * @throws IOException 
	 */
	private void toPageUserServlet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		//获取page参数
		Integer page = Integer.parseInt(req.getParameter("page"));
		
		//创建map集合用来封装数据
		 Map<String, Object> map = new HashMap<String, Object>();
		 
		 //获取List集合封装数据
		 List<User> list = new ArrayList<User>();
		 
		 UserDao dao = new UserDaoImpl();
		 
		 //获取总页数count
		 int pageCount = dao.selectPageCountByUserCount();
		 
		 //获取session对象
		 HttpSession session = req.getSession();
		 
		 //System.out.println(page);
		 
		 if(page < 1){
			 //页数小于1,直接设置页数为第一页
			 page = 1;
			 list = dao.selectUserByPage(page);
		 }else if(page > pageCount){
			 //大于最后一页,设置页数为最后一页
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
	 * 查询下一页数据
	 * @param req
	 * @param res
	 * @throws IOException 
	 */
	private void downPageUserServlet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		//创建map集合用来封装数据
		Map<String, Object> map = new HashMap<String, Object>();
		
		//存储用户数据
		List<User> list = new ArrayList<User>();
		
		//获取session对象
		HttpSession session = req.getSession();
		
		//获取当前页数
		map = (Map<String, Object>)session.getAttribute("map");
		Integer page = (Integer)map.get("page");
		//System.out.println(page);
		
		//获取功能类
		UserDao dao = new UserDaoImpl();
		//获取总页数
		Integer pageCount = dao.selectPageCountByUserCount();
		if(page >= pageCount){
			//最后一页再次点击下一页变为第一页
			page = pageCount;
			list = dao.selectUserByPage(page);
		}else{
			//正常进行下一页
			page++;
			list = dao.selectUserByPage(page);
		}
		
		//封装数据并进行重定向
		map.put("list", list);
		map.put("page", page);
		map.put("pageCount", pageCount);
		session.setAttribute("map", map);
		
		res.sendRedirect(req.getContextPath() + "/list.do");
	}

	/**
	 * 查询上一页用户数据
	 * @param req
	 * @param res
	 * @throws IOException 
	 */
	private void upPageUserServlet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		//创建map集合用来封装数据
		Map<String, Object> map = new HashMap<String, Object>();
		
		//存储用户数据
		List<User> list = new ArrayList<User>();
		
		//获取session对象
		HttpSession session = req.getSession();
		//获取当前页数
		map = (Map<String, Object>)session.getAttribute("map");
		Integer page = (Integer)map.get("page");
		//System.out.println(page);
		
		//获取工具类
		UserDao dao = new UserDaoImpl();
		//总页数
		int pageCount = dao.selectPageCountByUserCount();
		
		if(page < 2){
			//第一页如果继续点击上一页的话就跳到最后一页
			page = 1;
			//查询当前页的用户数据
			list = dao.selectUserByPage(page);
		}else{
			page--;//页数减一
			list = dao.selectUserByPage(page);
		}
		
		//封装数据
		map.put("list", list);
		map.put("page", page);
		map.put("pageCount", pageCount);
		session.setAttribute("map", map);
		
		res.sendRedirect(req.getContextPath() + "/list.do");
	}

	/**
	 * 根据用户ID修改用户数据
	 * @param req
	 * @param res
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void updateUserByIdServlet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		//获取用户参数
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("uname");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		
		//这里用重定向的原因是,需要传递错误信息
		HttpSession session = req.getSession();
		
		if(name == null || name.trim() == ""){
			session.setAttribute("nameMeg", "用户名不能为空!");
			res.sendRedirect(req.getContextPath() + "/select.do?id=" + id);
			return ;
		}
		if(name.contains(" ")){
			session.setAttribute("nameMeg", "用户名不能包含空格!");
			res.sendRedirect(req.getRequestURI() + "/select.do?id=" + id);
			return ;
		}
		
		UserDao dao = new UserDaoImpl();
		
		//用户名不能重复,但是用户名可以为自己原来的名字
		String oldName = dao.selectUserById(id).getUserName();
		if(!oldName.equals(name) && dao.selectUserByName(name)){
			session.setAttribute("nameMeg", "用户名已存在!");
			res.sendRedirect(req.getContextPath() + "/select.do?id=" + id);
			return ;
		}
		
		//封装用户数据
		User user = new User();
		user.setId(id);
		user.setUserName(name);
		user.setUserEmail(email);
		user.setUserPhone(phone);
		
		//修改用户数据
		int n = dao.updateUserMegById(user);
		if(n > 0){
			//修改成功跳转至用户列表页面
			res.sendRedirect(req.getContextPath() + "/list.do");
			//跳转至新的页面之后需要移除updateUser里面的信息
			session.removeAttribute("updateUser");
		}else{
			/*
			 * 修改失败跳转至根据ID查找用户页面,根据ID查找用户页面自动跳转至修改页面
			 * 因为需要先根据用户id查找到用户信息,才能够修改用户数据
			 */
			session.setAttribute("updateFailure", "修改失败,请联系工作人员!");
			res.sendRedirect(req.getContextPath() + "/select.do?id=" + id);
		}
	}

	/**
	 * 根据ID查找用户数据
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
			session.setAttribute("failure", "修改失败,请重新修改!");
			/*
			 * 重定向到list.do是因为,listUsers.jsp里面的信息是通过list.do传过去的,
			 * 直接访问listUsers.jsp会导致数据无法查出
			 */
			res.sendRedirect(req.getContextPath() + "/list.do");
		}
	}

	/**
	 * 根据ID删除用户
	 * @param req
	 * @param res
	 * @throws IOException 
	 */
	private void deleteUserByIdServlet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		//获取ID
		Integer id = Integer.parseInt(req.getParameter("id"));
		UserDao dao = new UserDaoImpl();
		dao.deleteUserById(id);
		res.sendRedirect(req.getContextPath() + "/list.do");
	}

	/**
	 * 处理用户登录功能
	 * @param req
	 * @param res
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void loginServlet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//获取参数
		String username = req.getParameter("uname");
		String password = req.getParameter("pwd");
		
		UserDao dao = new UserDaoImpl();
		if(!dao.selectUserByName(username)){
			req.setAttribute("nameMeg", "用户名不存在!");
			req.getRequestDispatcher("login.jsp").forward(req, res);
			return ;
		}
		
		/*
		 * 为用户密码加密,因为数据库里面存储的是已加密过的密码
		 * 所以判断密码是否正确需要先加密再匹配是否正确
		 */
		String salt = "干拌拉条子";
		password = DigestUtils.md5Hex(password + salt);
		
		User user = dao.loginByNameAndPwd(username, password);
		if(user == null){
			req.setAttribute("pwdMeg", "用户密码错误!");
			req.getRequestDispatcher("login.jsp").forward(req, res);
			return ;
		}
		
		HttpSession session = req.getSession();
		session.setAttribute("userLogined", user);
		res.sendRedirect(req.getContextPath() + "/list.do");
	}

	/**
	 * 查询用户列表功能
	 * @throws IOException 
	 * @throws ServletException
	 */
	private void selectUserList(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		UserDao dao = new UserDaoImpl();
		HttpSession session = req.getSession();
		
		Map<String, Object> map = new HashMap<>();
		if(session.getAttribute("map") == null){
			//System.out.println("呵呵");
			
			//默认为第一页
			int page = 1;
			List<User> list = new ArrayList<User>();
			list = dao.selectUserByPage(page);
			
			//封装数据
			map.put("list", list);
			map.put("page", page);
			int pageCount = dao.selectPageCountByUserCount();//查询总页数
			map.put("pageCount", pageCount);
			//绑定数据
			session.setAttribute("map", map);
		}
		
		req.getRequestDispatcher("listUsers.jsp").forward(req, res);
		/*
		 * 这个session属性是在update.do功能里面添加的错误提示
		 * 因为session的生命周期比较长,所以为了不影响后续修改功能,
		 * 需要在修改完成并显示用户列表之后清除该属性
		 */
		req.getSession().removeAttribute("nameMeg");
	}

	/**
	 * 用户注册功能
	 * @throws IOException 
	 * @throws ServletException
	 */
	private void registerServlet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		//获取用户参数
		String username = req.getParameter("uname");
		String password = req.getParameter("pwd");
		String userPhone = req.getParameter("phone");
		String userEmail = req.getParameter("email");
		
		if(username == null || username.trim() == ""){
			req.setAttribute("nameMeg", "用户名不能为空!");
			req.getRequestDispatcher("addUser.jsp").forward(req, res);
			return ;
		}
		
		if(username.contains(" ")){
			req.setAttribute("nameMeg", "用户名不能包含空格!");
			req.getRequestDispatcher("addUser.jsp").forward(req, res);
			return ;
		}
		
		UserDao dao = new UserDaoImpl();
		
		if(dao.selectUserByName(username)){
			req.setAttribute("nameMeg", "用户名已存在!");
			req.getRequestDispatcher("addUser.jsp").forward(req, res);
			return ;
		}
		
		//System.out.println(password);
		if(password == null || password.trim() == ""){
			req.setAttribute("pwdMeg", "用户密码不能为空");
			req.getRequestDispatcher("addUser.jsp").forward(req, res);
			return ;
		}
		
		if(password.contains(" ")){
			req.setAttribute("pwdMeg", "用户密码不能含有空格");
			req.getRequestDispatcher("addUser.jsp").forward(req, res);
			return ;
		}
		
		//盐
		String salt = "干拌拉条子";
		password = DigestUtils.md5Hex(password + salt);
		
		//封装用户数据
		User user = new User();
		user.setUserName(username);
		user.setPassword(password);
		user.setUserEmail(userEmail);
		user.setUserPhone(userPhone);
		
		int n = dao.register(user);
		if(n > 0){
			//注册成功
			res.sendRedirect("login.jsp");
		}else{
			//注册失败
			req.setAttribute("failure", "注册失败,请重新注册!");
			req.getRequestDispatcher("addUser.jsp").forward(req, res);
			return ;
		}
	}
	
	
}
