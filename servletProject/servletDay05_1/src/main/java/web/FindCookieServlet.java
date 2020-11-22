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
		//һ��Cookie�����װ��һ��Cookie�����е�����,��Cookie���ܻ᷵��null
		Cookie[] cs = req.getCookies();
		if(cs != null){
			for(Cookie c: cs){
				String name = c.getName();//��ȡcookie�е�����
				String value = c.getValue();//��ȡcookie�е�ֵ
				value = URLDecoder.decode(value, "utf-8");
				System.out.println(name + "," + value);
				pw.println(name + "," + value);
				pw.println("<br>");
			}
		}else{
			pw.println("û��Cookie");
		}
	}
}
