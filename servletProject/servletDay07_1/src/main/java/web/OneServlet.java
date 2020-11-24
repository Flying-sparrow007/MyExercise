package web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class OneServlet
 */
public class OneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	/*
    	 * 通过继承GenericServlet提供的方法来获取Servlet上下文
    	 */
    	ServletContext sc = getServletContext();
    	//绑定数据
    	sc.setAttribute("user", "用户");
    	
    	HttpSession session = request.getSession();
    	session.setMaxInactiveInterval(30);//设置session保鲜度30秒
    	session.setAttribute("role", "角色");
    }
}
