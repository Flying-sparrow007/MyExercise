package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;

public class UpdateUserServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		request.getRequestDispatcher("list").forward(request, response);
	}
}
