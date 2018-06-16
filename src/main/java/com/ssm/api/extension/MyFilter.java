package com.ssm.api.extension;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;

@WebFilter(filterName="MyFilter",urlPatterns="/*")
public class MyFilter implements Filter{

	@Override
	public void destroy() {
		System.out.println("销毁过滤器......");
	}

	/**
	 * 请求controller前执行
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		
		// 拿到 request 可以做一系列的操作，
//		例如获取  request 里面的东西，给 request set一些值，最后把这个request放到doFilter方面里面
		
		//读取流里面的东西，最后再把这里面的参数放在request里面，Controller读取request里面的值才会有
		WrappedHttpServletRequest wrappedRequest = new WrappedHttpServletRequest((HttpServletRequest)request);
		String param = this.getRequestBody(wrappedRequest);
		if(StringUtils.hasText(param)) {
			System.out.println(param);
			wrappedRequest.setAttribute("param", param);
		}
		filterChain.doFilter(wrappedRequest, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("过滤器init初始化.....");
	}
	
	public String getRequestBody(HttpServletRequest request){
        String body = null;
        try {
            body = IOUtils.toString(request.getReader());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return body;
    }

}
