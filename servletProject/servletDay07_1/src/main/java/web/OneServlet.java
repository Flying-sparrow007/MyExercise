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
    	 * ͨ���̳�GenericServlet�ṩ�ķ�������ȡServlet������
    	 */
    	ServletContext sc = getServletContext();
    	//������
    	sc.setAttribute("user", "�û�");
    	
    	HttpSession session = request.getSession();
    	session.setMaxInactiveInterval(30);//����session���ʶ�30��
    	session.setAttribute("role", "��ɫ");
    }
}
