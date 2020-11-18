package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
		//设置响应类型和字符集
		response.setContentType("text/html; charset = utf-8");
		//通过HttpServletResponse获取输出流
		PrintWriter pw = response.getWriter();
		
		//查询出所有的用户信息
		UserDao dao = new UserDaoImpl();
		List<User> list = dao.findAllUser();
		if(list == null){
			pw.println("没有数据!");
		}
		//依据查询出来的用户信息,输出表格
		pw.println("<table border = '>' width = '60%' cellpadding = '0'");
		pw.println("<tr><th>用户ID</th><th>用户名</th><th>邮箱</th><th>电话</th></tr>");
		for(User user: list){
			pw.println("<tr><td>" + user.getId() + "</td><td>" + user.getUserName() + "</td><td>" + user.getUserEmail() + "</td><td>" + user.getUserPhone() + "</td></tr>");
		}
		pw.println("</table>");
		pw.println("<form action = 'update'>");
		pw.println("<input style = 'margin: 10px 330px; height: 30px; width: 150px; text-align: center; line-height: 30px;' type = 'text' placeholder = '请输入要修改的用户id' name = 'id'>");
		pw.println("<input style = 'margin: 5px 330px; height: 30px; width: 150px; text-align: center; line-height: 30px;' type = 'text' placeholder = '请输入要修改的用户密码' name = 'password'>");
		pw.println("<input style = 'margin: 5px 370px; height: 30px; width: 50px; text-align: center; line-height: 30px;' type = 'submit' value = '提交'>");
		pw.println("</form>");
		
	}
}
