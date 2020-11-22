package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 查找有没有Cookie数据,如果有cookie数据,则查找有没有有一个名称
 * 为usernick,值为"ykt"的cookie,没有找到,需要添加
 * @author LENOVO
 *
 */
public class FindAddCookieServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置响应字符集
		response.setContentType("text/html; charset = utf-8");
		PrintWriter pw = response.getWriter();
		
		//获取所有的cookie
		Cookie[] cs = request.getCookies();
		//判断cookie中有没有数据
		if(cs != null){//若有数据则判断cookie中有没有名称是"usernick",若有则输出他的值
			boolean flag = false;
			for(Cookie c: cs){		
				if("usernick".equals(c.getName())){
					String name = c.getName();
					String value = c.getValue();
					pw.println("Cookie已找到,他的Cookie为: " + name + "=" + value);
					flag = true;
				}
			}
			if(!flag){
				Cookie c = new Cookie("usernick", "ykt");
				response.addCookie(c);
				pw.println("没有找到相应的Cookie数据,已添加!");
			}
		}else{//若没有数据则给cookie添加新的数据
			Cookie c = new Cookie("usernick", "ykt");
			response.addCookie(c);
			pw.println("没有找到相应的Cookie数据,已添加!");
		}
	}
}
