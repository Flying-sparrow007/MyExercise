package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
			System.out.println("hello");
		} catch (Exception e) {
			request.setAttribute("message", "系统繁忙,请稍后再试...");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	}
}
