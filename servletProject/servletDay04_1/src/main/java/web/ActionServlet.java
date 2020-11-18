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
		//获取请求资源绝对路径
		//System.out.println(request.getContextPath());
		//获取请求资源路径
		//System.out.println(request.getRequestURI());
		//获取请求资源完整路径
		//System.out.println(request.getRequestURL());
		//获取真实路径(是从当前的servlet在tomact中的存放文件夹开始)
		//System.out.println(request.getServletContext().getRealPath("/"));
		
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		/*uri = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		if("/add".equals(uri)){
			//处理添加业务
			System.out.println("处理添加业务!");
		}else if("/find".equals(uri)){
			//处理查询业务
			System.out.println("处理查询业务!");
		}*/
		
		//根据请求数据处理不同的业务
		if(uri.endsWith("add.do")){
			//System.out.println("添加业务!");
			//添加用户业务
			addUserServletAction(request, response);
		}else if(uri.endsWith("list.do")){
			//System.out.println("查询业务!");
			//查询用户列表业务
			findUserServletAction(request, response);
		} else if(uri.endsWith("delete.do")){
			//删除用户数据业务
			deleteUserByIdServletAction(request, response);
		}else if(uri.endsWith("login.do")){
			//用户登录业务
			loginUserServletAction(request, response);
		}else if(uri.endsWith("userById.do")){
			//根据用户id查询用户数据业务
			findUserByIdServletAction(request, response);
		}else if(uri.endsWith("update.do")){
			//修改用户数据业务
			updateUserServletAction(request, response);
		}else{
			
		}
		
	}

	//修改用户数据业务
	private void updateUserServletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置请求字符集
		request.setCharacterEncoding("utf-8");
		
		//获取参数
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("username");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		
		//把用户数据封装到user对象中
		User user = new User();
		user.setId(id);
		user.setUserName(name);
		user.setUserPhone(phone);
		user.setUserEmail(email);
		
		
		//修改用户数据
		UserDao dao = new UserDaoImpl();
		int n = dao.updateUserData(user);
		if(n < 1){
			request.setAttribute("message", "系统维护,请稍后再试...");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
		//转到list
		request.getRequestDispatcher("list.do").forward(request, response);
	}

	//根据用户ID查询用户数据业务
	private void findUserByIdServletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置响应
		response.setContentType("text/html; charset = utf-8");
		
		//获取参数,获取的是字符串,因此需要强转成Integer类型
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		//调用创建UserDao对象,调用UserDao接口中的方法
		UserDao dao = new UserDaoImpl();
		User user = dao.findUserById(id);
		//绑定user对象
		request.setAttribute("user", user);
		//获取转发器并转到updateUser.jsp
		request.getRequestDispatcher("updateUser.jsp").forward(request, response);
	}

	//登陆业务
	private void loginUserServletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//设置请求字符集
			request.setCharacterEncoding("utf-8");
			//设置响应类型和字符集
			response.setContentType("text/html; charset = utf-8");
			
			String userName = request.getParameter("username");
			String userPwd = request.getParameter("password");
			//System.out.println(userName + "," + userPwd);
			
			//判断用户名输入是否合法
			if(userName == null || userName.trim().isEmpty() || userName.contains(" ")){
				request.setAttribute("megName", "用户名输入有误,请重新输入!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return ;
			}
			
			//判断用户名是否存在
			UserDao dao = new UserDaoImpl();
			int count = dao.findUserByName(userName);
			if(count < 1){
				request.setAttribute("megName", "用户名不存在,请重新输入!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return ;
			}
			
			//盐
			String str = "干拌拉条子";
			
			//文件摘要并给原始密码加密
			String md5 = DigestUtils.md5Hex(userPwd + str);//用户输入的密码(已加密)
			System.out.println(md5);
			
			String password = dao.findUserPwdByName(userName);//数据库查询的密码(已加密)
			//判断密码是否正确
			if(!password.equals(md5)){
				request.setAttribute("megPwd", "密码错误,请重新输入!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return ;
			}
			
			//登陆成功(转发到用户列表页面)
			request.getRequestDispatcher("list.do").forward(request, response);
			
		} catch (Exception e) {
			request.setAttribute("message", "系统繁忙,请稍后再试...");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	//删除用户数据业务
	private void deleteUserByIdServletAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//设置响应类型
				response.setContentType("text/html; charset = utf-8");
				PrintWriter pw = response.getWriter();
				
				//获取请求参数
				Integer id = Integer.parseInt(request.getParameter("id"));
				
				//根据参数删除ID
				UserDao dao = new UserDaoImpl();
				int n = dao.deleteUserById(id);
				if(n < 1){
					throw new RuntimeException("系统维护,请稍后再试...");
				}
				//pw.println("删除成功!");
				//删除成功后重定向到list
				response.sendRedirect("list.do");
	}

	//查询用户业务
	private void findUserServletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//设置响应类型和字符集
			response.setContentType("text/html; charset = utf-8");
			//通过HttpServletResponse获取输出流
			PrintWriter pw = response.getWriter();
			
			//查询出所有的用户信息
			UserDao dao = new UserDaoImpl();
			List<User> list = dao.findAllUser();
			
			//用request绑定数据
			request.setAttribute("list", list);
			
			//获取request上所绑定的数据
			/*
			 * Object obj = request.getAttribute("list");//返回的是Object类型
			 * List<User> listUser = (List<User>)obj;
			 */
			
			
			//转发
			RequestDispatcher rd = request.getRequestDispatcher("listUsers.jsp");
			rd.forward(request, response);
			
			//转发之后后面的代码还会执行
			//System.out.println("hello");
		} catch (Exception e) {
			request.setAttribute("message", "系统繁忙,请稍后再试...");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	//添加用户业务
	private void addUserServletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//针对前端请求方式post有效
			request.setCharacterEncoding("UTF-8");
			//设置响应页面字符集
			response.setContentType("text/html; charset = UTF-8");
			
			//读取用户信息
			String userName = request.getParameter("username");
			String userPwd = request.getParameter("password");
			String userEmail = request.getParameter("email");
			String userPhone = request.getParameter("phone");
			
			//用户名不能为空
			if(userName == null || userName.trim().isEmpty()){
				request.setAttribute("meg", "用户名不能为空!");
				//获取转发器并转发
				request.getRequestDispatcher("addUser.jsp").forward(request, response);
				return ;
			}
			
			//用户名不能包含空格
			if(userName.contains(" ")){
				request.setAttribute("meg", "用户名不能包含空格!");
				request.getRequestDispatcher("addUser.jsp").forward(request, response);
				return ;
			}
			
			//用户名被占用
			UserDao dao = new UserDaoImpl();
			int count = dao.findUserByName(userName);
			if(count > 0){
				request.setAttribute("meg", "用户名被占用!");
				request.getRequestDispatcher("addUser.jsp").forward(request, response);
				return ;
			}
			
			//盐
			String str = "干拌拉条子";
			
			//文件摘要并且给原始密码加密
			String md5 = DigestUtils.md5Hex(userPwd + str);
			
			//将用户数据封装到User对象中
			User user = new User();
			user.setUserName(userName);
			user.setUserPwd(md5);
			user.setUserEmail(userEmail);
			user.setUserPhone(userPhone);
			
			//将User对象插入到数据库中
			dao.addUser(user);
			
			//获取输入流
			/*PrintWriter pw = response.getWriter();
			pw.println("添加成功");*/
			
			//重定向到登录页面
			/*
			 * HttpServletResponse提供了重定向方法 sendRedirect(String location)
			 */
			response.sendRedirect("login.jsp");
			
		} catch (Exception e) {
			request.setAttribute("message", "系统繁忙,请稍后再试...");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
}
