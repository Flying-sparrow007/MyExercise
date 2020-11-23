package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * HttpSession request.getSession(boolean flag)
 * 1.HttpSession��һ���ӿ�
 * 2.��flagΪtrueʱ(Ĭ��Ϊtrue),�Ȳ鿴�������Ƿ���sessionId,���û���򴴽�һ��sessionId,
 * �����sessionId,�����ݸ�sessionIdȥ���Ҷ�Ӧ��session����,
 * ����ҵ���,�򷵻ظö���,����Ҳ����򴴽�һ���µ�session����
 * 
 * ��flagΪfalseʱ,�Ȳ鿴�������Ƿ���session,���û���򷵻�null,
 * ����������sessionIdȥ���Ҷ�Ӧ��session����.
 * ����ҵ����򷵻ظö���,����Ҳ�������null
 * @author LENOVO
 *
 */
public class SomeServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡsession����
		HttpSession session = request.getSession();
		//��ȡsession����id
		String sessionId = session.getId();
		System.out.println(sessionId);
		
		//������
		//session.setAttribute("username", "admin");
		//request��������ֻ��һ������һ����Ӧ,��session��,�����޷������ض���,�ᶪʧ����
		//request.setAttribute("username", "admin");
		
		//��ȡsession�󶨵�����
		/*String name = (String)session.getAttribute("username");
		System.out.println(name);*/
		
		//�ض���
		//response.sendRedirect("some.jsp");
		//response.sendRedirect("some.jsp");
		
		Integer count = (Integer)session.getAttribute("count");
		if(count == null){
			count = 1;
		}else{
			count++;
		}
		
		session.setAttribute("count", count);
		response.setContentType("text/html; charset = utf-8");
		response.getWriter().println("��" + count + "�η���!");
	}
}
