package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet容器收到请求之后,会调用servlet的service方法来处理请求
 * 注:容器会实现创建好request和response,其中,request对象封装了请求数据包
 * 里面的所有数据,我们可以将处理结果写道response对象里面,然后容器负责从response对象
 * 获取处理结果,然后打包发送给浏览器
 * @author LENOVO
 *
 */
public class ServletHello extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置content-type消息头,告诉浏览器返回的数据类型
		response.setContentType("text/html");
		//通过response对象获取字符输出流
		PrintWriter pw = response.getWriter();
		/*
		 * 把数据写到response对象上,servlet容器会将response对象上存放的数据
		 * 取出来,打一个包(响应数据包),然后发送给浏览器
		 */
		pw.println("<h1>Hello World</h1>");
		//如果没有调用close(0方法,则容器会自动关闭流
		pw.close();
	}
}
