package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BmiServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * request提供了获取参数的方法getParemeter()方法
		 */
		//读取身高和体重
		String weight = request.getParameter("weight");
		String height = request.getParameter("height");
		System.out.println(weight + "," + height);
		/*
		 * 对于多选框,如果用户都不选,会返回null值,
		 * 当有多个请求参数名相同,应该使用getParameterValues()方法
		 * 返回String[]数组
		 */
		String[] interest = request.getParameterValues("interest");
		if(interest != null){
			for(String s: interest){
				System.out.println(s);
			}
		}
		
		//计算BMI(体质)指数 公式:体重 / 身高的平分
		double bmi = Double.parseDouble(weight) / (Double.parseDouble(height) * Double.parseDouble(height));
		String state = "正常";
		if(bmi < 19){
			state = "过轻";
		}
		if(bmi > 25){
			state = "过重";
		}
		
		/*
		 * 这行代码有两个作用:
		 * 1.设置Content-Type消息头,告诉浏览器服务器返回的数据类型以及编码格式
		 * 2.设置out在输出时,使用哪种字符集来编码
		 */
		response.setContentType("text/html;charset = utf-8");
		PrintWriter pw = response.getWriter();
		
		pw.println(state);
	}
}
