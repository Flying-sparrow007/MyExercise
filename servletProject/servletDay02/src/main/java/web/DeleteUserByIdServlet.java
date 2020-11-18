package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.UserDaoImpl;

public class DeleteUserByIdServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置响应类型
		response.setContentType("text/html; charset = utf-8");
		PrintWriter pw = response.getWriter();
		
		//获取请求参数
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		//根据参数删除ID
		UserDao dao = new UserDaoImpl();
		int n = dao.deleteUserById(id);
		if(n > 0){
			pw.println("删除成功!");
		}else{
			throw new RuntimeException("系统维护,请稍后再试...");
		}
		
	}
}
