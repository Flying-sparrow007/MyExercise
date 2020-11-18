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
			PrintWriter pw = response.getWriter();
			pw.println("添加成功");
		} catch (Exception e) {
			request.setAttribute("message", "系统繁忙,请稍后再试...");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	}
}
