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
    	System.out.println("哈哈");
    	
    	//设置请求字符集
    	request.setCharacterEncoding("utf-8");
    	//获取请求参数
    	String content = request.getParameter("content");
    	
    	//设置响应字符集
    	response.setContentType("text/html; charset = utf-8");
    	
    	//响应
    	response.getWriter().println("你的评论内容是:" + content);
    }

}
