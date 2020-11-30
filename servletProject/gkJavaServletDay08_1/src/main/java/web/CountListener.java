package web;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 被监听的类,需要实现接口HttpSessionLintener
 * @author LENOVO
 *
 */
public class CountListener implements HttpSessionListener {
	
	public CountListener(){
		System.out.println("CountListener的构造器!");
	}

	/*
	 * session对象创建之后执行sessionCreated(HttpSessionEvent se)方法
	 * 监听器也是基于sessionId来控制的
	 * se是事件对象
	 */
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("CountListener的sessionCreated()方法!");
		//1.通过事件源se获取session
		HttpSession session = se.getSession();
		session.setMaxInactiveInterval(10);//设置session生存时间为10秒
		//2.通过session获取上下文
		ServletContext context = session.getServletContext();
		//3.通过上下文获取在线人数(浏览人数)
		Integer count = (Integer)context.getAttribute("count");
		if(count == null){
			//第一个用户
			count = 1;
		}else{
			//不是第一个用户
			count++;
		}
		context.setAttribute("count", count);
		
	}

	/*
	 * session销毁
	 * 1.session过期
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("sessionDestoryed方法");
	}

}
