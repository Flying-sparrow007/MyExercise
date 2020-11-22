package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindCookieServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset = utf-8");
		PrintWriter pw = res.getWriter();
		//一个Cookie对象封装了一个Cookie中所有的数据,该Cookie可能会返回null
		Cookie[] cs = req.getCookies();
		if(cs != null){
			for(Cookie c: cs){
				String name = c.getName();//获取cookie中的名称
				String value = c.getValue();//获取cookie中的值
				value = URLDecoder.decode(value, "utf-8");
				System.out.println(name + "," + value);
				pw.println(name + "," + value);
				pw.println("<br>");
			}
		}else{
			pw.println("没有Cookie");
		}
	}
}
