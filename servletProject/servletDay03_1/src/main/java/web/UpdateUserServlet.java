package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.UserDaoImpl;

public class UpdateUserServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置请求字符集
		request.setCharacterEncoding("UTF-8");
		//设置响应
		response.setContentType("text/html; charset = utf-8");
		PrintWriter pw = response.getWriter();
		
		//获取参数
		Integer id = Integer.parseInt(request.getParameter("id"));
		String password = request.getParameter("password");
		
		UserDao dao = new UserDaoImpl();
		int n = dao.updateUserPwdById(id, password);
		if(n > 0){
			pw.println("修改成功!");
		}else{
			pw.println("修改失败!");
		}
	}
}
