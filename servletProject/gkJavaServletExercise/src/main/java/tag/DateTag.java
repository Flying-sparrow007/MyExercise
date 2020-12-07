package tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class DateTag extends SimpleTagSupport {
	private String timeFormat;//��ǩ�е�����
	
	@Override
	public void doTag() throws JspException, IOException {
		//����ʱ��ת����ʽ
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
		//��ȡJsp������
		PageContext p = (PageContext)getJspContext();
		//��ȡJsp�����
		JspWriter writer = p.getOut();
		
		//����ʱ��
		Date date = new Date();
		
		//����ʱ��ת�������
		writer.println(sdf.format(date));
	}

	/*
	 * set����������,get��������û��
	 * ��Ϊ��Ҫͨ����ǩΪ�����Ը�ֵ
	 */
	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}
	
	
}
