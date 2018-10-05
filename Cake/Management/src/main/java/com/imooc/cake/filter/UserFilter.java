package com.imooc.cake.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *
 * 处理登录和未登录情况
 *
 * @version 1.0
 */
public class UserFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if ("/login.do".equals(((HttpServletRequest)servletRequest).getServletPath()) ||
                "/loginPrompt.do".equals(((HttpServletRequest)servletRequest).getServletPath())) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (null != ((HttpServletRequest)servletRequest).getSession().getAttribute("username")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            servletRequest.getRequestDispatcher("/loginPrompt.do").forward(servletRequest, servletResponse);
        }
    }

    public void destroy() {

    }
}
