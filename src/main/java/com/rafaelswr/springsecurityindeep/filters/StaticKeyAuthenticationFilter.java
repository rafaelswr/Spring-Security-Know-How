package com.rafaelswr.springsecurityindeep.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;


//@Component
public class StaticKeyAuthenticationFilter implements Filter {

    @Value("${auth:value}")
    private String authKey;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var httpRequest = (HttpServletRequest) request;
        var httpResponse = (HttpServletResponse) response;

        String authString = httpRequest.getHeader("Request-Id");
        if(authString.equals("12345")){
            chain.doFilter(request, response);
        }else{
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

    }
}
