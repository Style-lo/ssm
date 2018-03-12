package com.ssm.api.extension;

import javax.net.ssl.HandshakeCompletedListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.api.bean.request.User;

public class Myinter implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("action之前执行");
		if (request.getSession() != null) {
			//在这里做登录拦截
			Object attribute = request.getSession().getAttribute("user");
			if (attribute == null) {
//				response.getWriter().println("/jsp/login.jsp");
				request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);  
				return false;
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("action之后执行，生成试图前");
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("最后执行");
		
	}

}
