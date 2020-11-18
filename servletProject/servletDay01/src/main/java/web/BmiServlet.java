package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BmiServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * request�ṩ�˻�ȡ�����ķ���getParemeter()����
		 */
		//��ȡ��ߺ�����
		String weight = request.getParameter("weight");
		String height = request.getParameter("height");
		System.out.println(weight + "," + height);
		/*
		 * ���ڶ�ѡ��,����û�����ѡ,�᷵��nullֵ,
		 * ���ж�������������ͬ,Ӧ��ʹ��getParameterValues()����
		 * ����String[]����
		 */
		String[] interest = request.getParameterValues("interest");
		if(interest != null){
			for(String s: interest){
				System.out.println(s);
			}
		}
		
		//����BMI(����)ָ�� ��ʽ:���� / ��ߵ�ƽ��
		double bmi = Double.parseDouble(weight) / (Double.parseDouble(height) * Double.parseDouble(height));
		String state = "����";
		if(bmi < 19){
			state = "����";
		}
		if(bmi > 25){
			state = "����";
		}
		
		/*
		 * ���д�������������:
		 * 1.����Content-Type��Ϣͷ,������������������ص����������Լ������ʽ
		 * 2.����out�����ʱ,ʹ�������ַ���������
		 */
		response.setContentType("text/html;charset = utf-8");
		PrintWriter pw = response.getWriter();
		
		pw.println(state);
	}
}
