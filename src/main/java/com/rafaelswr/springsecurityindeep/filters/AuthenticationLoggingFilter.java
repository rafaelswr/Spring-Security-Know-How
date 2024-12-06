package com.rafaelswr.springsecurityindeep.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.logging.Logger;

public class AuthenticationLoggingFilter extends OncePerRequestFilter {
    private final Logger logger = Logger.getLogger(AuthenticationLoggingFilter.class.getName());

    /**. OncePerRequestFilter implements logic to make
     sure that the filter’s doFilter() method is executed only one time per request.*/
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String responseMessage = response.getHeader("message");

        if(responseMessage != null){
            logger.info("Message: " + responseMessage);
        }else{
            logger.info("Só recebi agora :D");
        }

        filterChain.doFilter(request, response);

    }


     /*
    WHEN implements Filter {...}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var requestId = (HttpServletRequest) request;

        var headerRequestID = requestId.getHeader("Request-Id");

        logger.info("Successfully Authenticated with ID: " + headerRequestID);

        chain.doFilter(request, response);
    }
    */
}
