package web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCookieServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建Cookie对象
		Cookie c = new Cookie("username", "kun");
		//响应Cookie
		response.addCookie(c);
		/*
		 * Cookie生存时间问题:
		 * 1.默认情况下,浏览器会把cookie保存在内存里面,浏览器只要不关闭,cookie就会一直存在,浏览器关闭则cookie会被删除
		 * 2.可以用setMaxAge方法来设置Cookie的生存时间
		 */
		Cookie c2 = new Cookie("city", "xian");
		//设置Cookie生存时间
		c2.setMaxAge(360*24*60*60);
		response.addCookie(c2);
		//删除cookie,比如删除一个名称为city的cookie
		c2.setMaxAge(0);
		response.addCookie(c2);
		
		/*
		 * URLEncoder类包含一个encode(String s, String charset)静态方法,它可以将
		 * 普通字符串转换为application/x-www=form-urlencode字符串
		 * 
		 * URLDecoder类包含一个decode(String s, String charset)静态方法,它可以将
		 * 看上去乱码的特殊字符串转换成正确字符串
		 */
		String str = "李总";
		str = URLEncoder.encode(str, "utf-8");
		Cookie c3 = new Cookie("student", str);
		response.addCookie(c3);
	}
}
