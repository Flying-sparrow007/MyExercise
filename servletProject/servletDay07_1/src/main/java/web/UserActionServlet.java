package web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserActionServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private Double min;
	private Double max;
	
	//��ȡ��ʼ������
	/*@Override
	public void init() throws ServletException {
		min = Double.parseDouble(getInitParameter("min"));
		max = Double.parseDouble(getInitParameter("max"));
	}*/
	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext sc = config.getServletContext();
		max = Double.parseDouble(sc.getInitParameter("max"));
		min = Double.parseDouble(sc.getInitParameter("min"));
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		if(uri.endsWith("bmi.do")){
			//����BMIָ��
			bimServlet(request, response);
		}
	}

	private void bimServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//������Ӧ��ʽ���ַ���
		response.setContentType("text/html; charset = utf-8");
		
		//��ȡ����
		Double height = Double.parseDouble(request.getParameter("height"));
		Double weight = Double.parseDouble(request.getParameter("weight"));
		
		Double bmi = weight / height / height;
		System.out.println(bmi);
		
		//�ж��û�BMIָ����״̬
		String state = "����";
		if(bmi > max){
			state = "����";
		}
		if(bmi < min){
			state = "����";
		}
		
		response.getWriter().println(state);
	}
}
