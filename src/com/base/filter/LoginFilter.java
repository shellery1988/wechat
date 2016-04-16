package com.base.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

@Controller
public class LoginFilter implements Filter{
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
	@Override
	public void destroy() {
		
	}
	
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		String url = request.getRequestURI();
		if (url.indexOf("/weixin/") == -1) {
			String location = "/login-filter.jsp";
			request.getRequestDispatcher(location).forward(request, response);
			response.setHeader("Cache-Control", "no-store");
			response.setDateHeader("Expires", 0);
			response.setHeader("Pragma", "no-cache");
		} else {
			chain.doFilter(request, response);
		}
	}

	
}
