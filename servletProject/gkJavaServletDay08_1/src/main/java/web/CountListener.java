package web;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * ����������,��Ҫʵ�ֽӿ�HttpSessionLintener
 * @author LENOVO
 *
 */
public class CountListener implements HttpSessionListener {
	
	public CountListener(){
		System.out.println("CountListener�Ĺ�����!");
	}

	/*
	 * session���󴴽�֮��ִ��sessionCreated(HttpSessionEvent se)����
	 * ������Ҳ�ǻ���sessionId�����Ƶ�
	 * se���¼�����
	 */
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("CountListener��sessionCreated()����!");
		//1.ͨ���¼�Դse��ȡsession
		HttpSession session = se.getSession();
		session.setMaxInactiveInterval(10);//����session����ʱ��Ϊ10��
		//2.ͨ��session��ȡ������
		ServletContext context = session.getServletContext();
		//3.ͨ�������Ļ�ȡ��������(�������)
		Integer count = (Integer)context.getAttribute("count");
		if(count == null){
			//��һ���û�
			count = 1;
		}else{
			//���ǵ�һ���û�
			count++;
		}
		context.setAttribute("count", count);
		
	}

	/*
	 * session����
	 * 1.session����
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("sessionDestoryed����");
	}

}
