package web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommentFilter implements Filter {

	//容器启动之后,会立刻将过滤器实例化,只会创建一次
	public CommentFilter() {
		System.out.println("这是CommentFilter的构造器!");
	}
	
	/*
	 * 实例化之后容器会立刻调用init方法完成初始化操作.
	 * 1.容器会将FilterConfig对象作为参数传递过来。
	 * 2.FilterConfig对象提供了读取初始化参数的方法(getInitParameter)
	 * 3.init()方法只执行一次
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//String name = filterConfig.getInitParameter("username");//获取局部参数
		String name = filterConfig.getServletContext().getInitParameter("username");//获取全局参数
		System.out.println("这是CommentFilter的初始方法,它的初始化参数是:" + name);
	}

	/*
	 * 容器收到请求之后,会调用doFilter方法,
	 * 来处理请求(相当于service方法),容器会将request对象和response对象
	 * 作为参数传递过来
	 * a.ServletRequest是HttpServletRequest的父接口
	 * b.ServletResponse是HttpServletResponse的父接口
	 * c.如果调用了FilterChain(过滤器链)的doFilter方法,则容器会向后调用,
	 * 否则中断请求,返回处理结果
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		/*
		 * (了解)因为Sun过渡设计,在这里我们真正要调用的是子接口中的声明方法
		 */
		System.out.println("Filter开始");
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		//设置请求字符集
		req.setCharacterEncoding("utf-8");
		//设置响应字符集
		res.setContentType("text/html; charset = utf-8");
		//获取请求参数
		String content = req.getParameter("content");
		//处理敏感词或词语
		if(content.contains("卧槽")){
			res.getWriter().println("请注意用词!");
		}else{//通过
			chain.doFilter(req, res);
			System.out.println("Filter结束!");
		}
		
	}

	@Override
	public void destroy() {
		System.out.println("这是CommentFilter的销毁方法!");
	}

}
