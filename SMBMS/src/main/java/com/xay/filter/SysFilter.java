package com.xay.filter;

import com.xay.pojo.User;
import com.xay.util.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SysFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        //过滤器，从Session中获取用户
        User user = (User) httpServletRequest.getSession().getAttribute(Constants.USER_SESSION);

        if(user==null){
            //已经被移除或者注销了，或者没有登录
            httpServletResponse.sendRedirect("/SMBMS/error.jsp");
            return;
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
