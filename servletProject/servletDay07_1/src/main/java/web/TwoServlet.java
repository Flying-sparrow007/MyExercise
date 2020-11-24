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
    	//��ȡservlet���������󶨵�����
    	ServletContext sc = getServletContext();
    	String scName = (String)sc.getAttribute("user");
    	System.out.println("servlet�󶨵�������:" + scName);
    	
    	//��ȡsession���󶨵�����
    	HttpSession session = request.getSession();
    	String seName = (String)session.getAttribute("role");
    	System.out.println("session�󶨵�������:" + seName);
    }

}
