package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

import dao.UserDao;
import dao.UserDaoImpl;

public class LoginUserServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			request.getRequestDispatcher("list").forward(request, response);
			
		} catch (Exception e) {
			request.setAttribute("message", "系统繁忙,请稍后再试...");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	}
}
