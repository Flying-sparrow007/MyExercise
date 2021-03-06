package web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class TwoServlet
 */
public class TwoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//获取servlet上下文所绑定的数据
    	ServletContext sc = getServletContext();
    	String scName = (String)sc.getAttribute("user");
    	System.out.println("servlet绑定的数据是:" + scName);
    	
    	//获取session所绑定的数据
    	HttpSession session = request.getSession();
    	String seName = (String)session.getAttribute("role");
    	System.out.println("session绑定的数据是:" + seName);
    }

}
