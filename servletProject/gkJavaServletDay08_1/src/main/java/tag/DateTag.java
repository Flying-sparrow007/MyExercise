package tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * �Զ���JSP��ǩ,��Ҫ�̳�SimpleTagSupport��
 * @author LENOVO
 *
 */
public class DateTag extends SimpleTagSupport {
	private String pattern;//JSP��ǩ�е�����
	
	
	@Override
	public void doTag() throws JspException, IOException {
		//�������ڸ�ʽ
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		//��ȡjsp������
		PageContext page = (PageContext)getJspContext();
		//��ȡjsp��out��������JspWriter
		JspWriter out = page.getOut();
		//����ʱ��
		Date date = new Date();
		//��dateд��ȥ
		out.println(sdf.format(date));
	}

	/*
	 * ���������Ҫд���Ե�set����
	 * get���Բ�Ҫ,set����Ҫ
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
}
