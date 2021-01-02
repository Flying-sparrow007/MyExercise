package com.gk.login.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.gk.login.entity.User;

/**
 * ������Ҫʵ�ֽӿ�HandlerInterceptor
 * @author LENOVO
 *
 */
@Component
public class UserHandlerInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("session��֤��ʼ...");
		//��ȡsession����
		HttpSession session = request.getSession();
		//ͨ��session�����ȡ������
		User user = (User)session.getAttribute("user");
		if(user == null){
			//�û�û�е�¼��Ҫ����
			//�ض��򵽵�¼ҳ��
			response.sendRedirect(request.getContextPath() + "/user/toLogin.do");
			return false;
		}
		//�û���¼��,�������,������
		return true;
	}

	/*
	 * ����������ɹ�֮���ڷ���ҳ��֮ǰ��ִ�и�postHandle����
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle");
	}

	/*
	 * ���������󷵻�ҳ��ɹ���ִ�и÷���
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion");
	}

}
