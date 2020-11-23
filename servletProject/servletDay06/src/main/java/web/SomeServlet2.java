package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * session超时:
 * 服务器会将空闲时间过长的session对象删除掉,这样做的目的是为了
 * 节省内存空间,默认session时间长度为30分钟
 * @author LENOVO
 *
 */
public class SomeServlet2 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//设置session时间长度  优先级: 类中的设置 > 项目的web.xml中的设置 > server服务器中的设置  就近原则
		//此处设置的时间单位是秒  web.xml文件中设置的时间单位是分钟
		session.setMaxInactiveInterval(5);
		
		//绑定数据
		session.setAttribute("username", "admin");
		session.setAttribute("age", 29);
		
		//移出绑定数据
		session.removeAttribute("username");
		
		//转发
		request.getRequestDispatcher("some.jsp").forward(request, response);
	}
}
