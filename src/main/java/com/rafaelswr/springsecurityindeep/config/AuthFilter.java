package com.rafaelswr.springsecurityindeep.config;

import com.rafaelswr.springsecurityindeep.filters.RequestValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AuthFilter {

    private final AuthProviderConf authenticationProvider;

    @Autowired
    public AuthFilter(AuthProviderConf authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

            //(demo) filter that make a validation if the request has a header called Request-ID (example)
            httpSecurity.addFilterBefore(new RequestValidationFilter(), BasicAuthenticationFilter.class);

            // httpSecurity.httpBasic(Customizer.withDefaults());
            httpSecurity.authorizeHttpRequests(request->{
                        request.requestMatchers("/hello").authenticated();
                        request.requestMatchers("/other").hasRole("read");
                        request.requestMatchers("/user/create").permitAll();
                        request.anyRequest().permitAll();

                    }).formLogin(Customizer.withDefaults());

            httpSecurity.authenticationProvider(authenticationProvider);

            httpSecurity.csrf().disable();

            return httpSecurity.build();
    }

}
