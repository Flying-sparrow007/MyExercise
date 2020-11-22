package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ������û��Cookie����,�����cookie����,�������û����һ������
 * Ϊusernick,ֵΪ"ykt"��cookie,û���ҵ�,��Ҫ���
 * @author LENOVO
 *
 */
public class FindAddCookieServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//������Ӧ�ַ���
		response.setContentType("text/html; charset = utf-8");
		PrintWriter pw = response.getWriter();
		
		//��ȡ���е�cookie
		Cookie[] cs = request.getCookies();
		//�ж�cookie����û������
		if(cs != null){//�����������ж�cookie����û��������"usernick",�������������ֵ
			boolean flag = false;
			for(Cookie c: cs){		
				if("usernick".equals(c.getName())){
					String name = c.getName();
					String value = c.getValue();
					pw.println("Cookie���ҵ�,����CookieΪ: " + name + "=" + value);
					flag = true;
				}
			}
			if(!flag){
				Cookie c = new Cookie("usernick", "ykt");
				response.addCookie(c);
				pw.println("û���ҵ���Ӧ��Cookie����,�����!");
			}
		}else{//��û���������cookie����µ�����
			Cookie c = new Cookie("usernick", "ykt");
			response.addCookie(c);
			pw.println("û���ҵ���Ӧ��Cookie����,�����!");
		}
	}
}
