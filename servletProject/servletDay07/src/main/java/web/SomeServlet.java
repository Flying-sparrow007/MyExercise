package web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SomeServlet extends HttpServlet {
	//1.servlet构造器
	public SomeServlet(){
		System.out.println("SomeServlet的构造器");
	}
	
	//3.调用service
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("SomeServlet的service方法");
	}
	
	/*
	 * 2.通过Override GenericServlet的init方法,来实现自己的
	 * 初始化处理逻辑
	 */
	@Override
	public void init() {
		System.out.println("SomeServlet的初始化");
		//获取局部初始化参数
		String name = getInitParameter("username");
		System.out.println("获取到的初始化参数是:" + name);
	}
	
	/*
	 * 4.关闭服务器即将销毁
	 * 通过Override GenericServlet的destroy方法,来实现自己的
	 */
	@Override
	public void destroy() {
		System.out.println("SomeServlet的销毁方法");
	}
	
	//获取全局初始化参数
	@Override
	public void init(ServletConfig config) throws ServletException {
		String name = config.getServletContext().getInitParameter("username");
		System.out.println(name);
	}
}
