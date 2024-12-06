package com.rafaelswr.springsecurityindeep.authHandlerConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            var authorities = authentication.getAuthorities();
            var auth = authorities.stream()
                    .filter(a->a.getAuthority().equals("read"))
                    .findFirst();
        if (auth.isPresent()) {
            response.sendRedirect("/home");
        }else{
            response.sendRedirect("/error");
        }
    }
}
