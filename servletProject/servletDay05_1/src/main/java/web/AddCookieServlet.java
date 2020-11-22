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
		//����Cookie����
		Cookie c = new Cookie("username", "kun");
		//��ӦCookie
		response.addCookie(c);
		/*
		 * Cookie����ʱ������:
		 * 1.Ĭ�������,��������cookie�������ڴ�����,�����ֻҪ���ر�,cookie�ͻ�һֱ����,������ر���cookie�ᱻɾ��
		 * 2.������setMaxAge����������Cookie������ʱ��
		 */
		Cookie c2 = new Cookie("city", "xian");
		//����Cookie����ʱ��
		c2.setMaxAge(360*24*60*60);
		response.addCookie(c2);
		//ɾ��cookie,����ɾ��һ������Ϊcity��cookie
		c2.setMaxAge(0);
		response.addCookie(c2);
		
		/*
		 * URLEncoder�����һ��encode(String s, String charset)��̬����,�����Խ�
		 * ��ͨ�ַ���ת��Ϊapplication/x-www=form-urlencode�ַ���
		 * 
		 * URLDecoder�����һ��decode(String s, String charset)��̬����,�����Խ�
		 * ����ȥ����������ַ���ת������ȷ�ַ���
		 */
		String str = "����";
		str = URLEncoder.encode(str, "utf-8");
		Cookie c3 = new Cookie("student", str);
		response.addCookie(c3);
	}
}
