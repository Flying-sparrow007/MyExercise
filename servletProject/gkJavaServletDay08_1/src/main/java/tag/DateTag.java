package tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * 自定义JSP标签,需要继承SimpleTagSupport类
 * @author LENOVO
 *
 */
public class DateTag extends SimpleTagSupport {
	private String pattern;//JSP标签中的属性
	
	
	@Override
	public void doTag() throws JspException, IOException {
		//创建日期格式
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		//获取jsp上下文
		PageContext page = (PageContext)getJspContext();
		//获取jsp的out方法返回JspWriter
		JspWriter out = page.getOut();
		//创建时间
		Date date = new Date();
		//把date写出去
		out.println(sdf.format(date));
	}

	/*
	 * 在这里必须要写属性的set方法
	 * get可以不要,set必须要
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
}
