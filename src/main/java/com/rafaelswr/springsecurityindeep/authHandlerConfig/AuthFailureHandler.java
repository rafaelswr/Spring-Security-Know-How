package com.rafaelswr.springsecurityindeep.authHandlerConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class AuthFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        var auth = SecurityContextHolder.getContext().getAuthentication();

        try{
            response.setHeader("failed", LocalDateTime.now().toString());
            response.sendRedirect("/login");

        }catch (IOException ex){
            throw new RuntimeException();
        }

    }
}
