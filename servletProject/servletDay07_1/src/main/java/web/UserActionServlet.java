package web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserActionServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private Double min;
	private Double max;
	
	//获取初始化参数
	/*@Override
	public void init() throws ServletException {
		min = Double.parseDouble(getInitParameter("min"));
		max = Double.parseDouble(getInitParameter("max"));
	}*/
	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext sc = config.getServletContext();
		max = Double.parseDouble(sc.getInitParameter("max"));
		min = Double.parseDouble(sc.getInitParameter("min"));
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		if(uri.endsWith("bmi.do")){
			//计算BMI指数
			bimServlet(request, response);
		}
	}

	private void bimServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//设置响应格式和字符集
		response.setContentType("text/html; charset = utf-8");
		
		//获取参数
		Double height = Double.parseDouble(request.getParameter("height"));
		Double weight = Double.parseDouble(request.getParameter("weight"));
		
		Double bmi = weight / height / height;
		System.out.println(bmi);
		
		//判断用户BMI指数的状态
		String state = "正常";
		if(bmi > max){
			state = "过重";
		}
		if(bmi < min){
			state = "过轻";
		}
		
		response.getWriter().println(state);
	}
}
