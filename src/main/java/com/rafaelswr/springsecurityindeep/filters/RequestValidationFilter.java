package com.rafaelswr.springsecurityindeep.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class RequestValidationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var httpRequest = (HttpServletRequest) servletRequest;
        var httpResponse = (HttpServletResponse) servletResponse;

        String requestId = httpRequest.getHeader("Request-Id");

        if(requestId == null || requestId.isBlank()){
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }
}
