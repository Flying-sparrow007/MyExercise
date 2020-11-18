package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;

public class FindUserByIdServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
}
