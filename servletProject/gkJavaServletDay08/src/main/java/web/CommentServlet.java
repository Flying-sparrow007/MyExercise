package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CommentServlet
 */
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("����");
    	
    	//���������ַ���
    	request.setCharacterEncoding("utf-8");
    	//��ȡ�������
    	String content = request.getParameter("content");
    	
    	//������Ӧ�ַ���
    	response.setContentType("text/html; charset = utf-8");
    	
    	//��Ӧ
    	response.getWriter().println("�������������:" + content);
    }

}
