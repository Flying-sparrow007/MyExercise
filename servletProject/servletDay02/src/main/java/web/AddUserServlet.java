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
		//针对post有效
		request.setCharacterEncoding("UTF-8");
		//设置响应页面字符集
		response.setContentType("text/html; charset = UTF-8");
		
		//读取用户信息
		String userName = request.getParameter("username");
		String userPwd = request.getParameter("password");
		String userEmail = request.getParameter("email");
		String userPhone = request.getParameter("phone");
		
		//将用户数据封装到User对象中
		User user = new User();
		user.setUserName(userName);
		user.setUserPwd(userPwd);
		user.setUserEmail(userEmail);
		user.setUserPhone(userPhone);
		
		//将User对象插入到数据库中
		UserDao dao = new UserDaoImpl();
		int n = dao.addUser(user);
		if(n < 1){
			throw new RuntimeException("系统维护,请稍后再试...");
		}		
		//获取输入流
		PrintWriter pw = response.getWriter();
		pw.println("添加成功");
	}
}
