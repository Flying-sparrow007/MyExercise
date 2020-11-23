package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * session��ʱ:
 * �������Ὣ����ʱ�������session����ɾ����,��������Ŀ����Ϊ��
 * ��ʡ�ڴ�ռ�,Ĭ��sessionʱ�䳤��Ϊ30����
 * @author LENOVO
 *
 */
public class SomeServlet2 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//����sessionʱ�䳤��  ���ȼ�: ���е����� > ��Ŀ��web.xml�е����� > server�������е�����  �ͽ�ԭ��
		//�˴����õ�ʱ�䵥λ����  web.xml�ļ������õ�ʱ�䵥λ�Ƿ���
		session.setMaxInactiveInterval(5);
		
		//������
		session.setAttribute("username", "admin");
		session.setAttribute("age", 29);
		
		//�Ƴ�������
		session.removeAttribute("username");
		
		//ת��
		request.getRequestDispatcher("some.jsp").forward(request, response);
	}
}
