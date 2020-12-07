package tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class DateTag extends SimpleTagSupport {
	private String timeFormat;//标签中的属性
	
	@Override
	public void doTag() throws JspException, IOException {
		//创建时间转换格式
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
		//获取Jsp上下问
		PageContext p = (PageContext)getJspContext();
		//获取Jsp输出流
		JspWriter writer = p.getOut();
		
		//创建时间
		Date date = new Date();
		
		//进行时间转化并输出
		writer.println(sdf.format(date));
	}

	/*
	 * set方法必须有,get方法可以没有
	 * 因为需要通过标签为该属性赋值
	 */
	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}
	
	
}
