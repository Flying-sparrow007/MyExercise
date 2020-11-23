package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * HttpSession request.getSession(boolean flag)
 * 1.HttpSession是一个接口
 * 2.当flag为true时(默认为true),先查看请求中是否有sessionId,如果没有则创建一个sessionId,
 * 如果有sessionId,则依据该sessionId去查找对应的session对象,
 * 如果找到了,则返回该对象,如果找不到则创建一个新的session对象
 * 
 * 当flag为false时,先查看请求当中是否有session,如果没有则返回null,
 * 如果有则根据sessionId去查找对应的session对象.
 * 如果找到了则返回该对象,如果找不到返回null
 * @author LENOVO
 *
 */
public class SomeServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取session对象
		HttpSession session = request.getSession();
		//获取session对象id
		String sessionId = session.getId();
		System.out.println(sessionId);
		
		//绑定数据
		//session.setAttribute("username", "admin");
		//request生命周期只是一次请求一次响应,比session短,所以无法进行重定型,会丢失数据
		//request.setAttribute("username", "admin");
		
		//获取session绑定的数据
		/*String name = (String)session.getAttribute("username");
		System.out.println(name);*/
		
		//重定向
		//response.sendRedirect("some.jsp");
		//response.sendRedirect("some.jsp");
		
		Integer count = (Integer)session.getAttribute("count");
		if(count == null){
			count = 1;
		}else{
			count++;
		}
		
		session.setAttribute("count", count);
		response.setContentType("text/html; charset = utf-8");
		response.getWriter().println("第" + count + "次访问!");
	}
}
