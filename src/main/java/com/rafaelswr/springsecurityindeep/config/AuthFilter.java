package com.rafaelswr.springsecurityindeep.config;

import com.rafaelswr.springsecurityindeep.authHandlerConfig.AuthFailureHandler;
import com.rafaelswr.springsecurityindeep.authHandlerConfig.AuthSuccessHandler;
import com.rafaelswr.springsecurityindeep.filters.AuthenticationLoggingFilter;
import com.rafaelswr.springsecurityindeep.filters.CsrfTokenLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
@EnableWebSecurity
public class AuthFilter {

    private final AuthFailureHandler authFailureHandler;

    private final AuthSuccessHandler authSuccessHandler;


    private final AuthProviderConf authenticationProvider;

    @Autowired
    public AuthFilter(AuthFailureHandler authFailureHandler, AuthSuccessHandler authSuccessHandler, AuthProviderConf authenticationProvider) {
        this.authFailureHandler = authFailureHandler;
        this.authSuccessHandler = authSuccessHandler;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

            // (Demo) Filter that validates if the request contains a header called 'Request-ID' (example),
            // and another one after the BasicAuthenticationFilter to log the value of the 'Request-ID' header.
            /*httpSecurity.addFilterBefore(new RequestValidationFilter(), BasicAuthenticationFilter.class)
                    .addFilterAt(new StaticKeyAuthenticationFilter(), BasicAuthenticationFilter.class).addFilterAfter(new AuthenticationLoggingFilter(), BasicAuthenticationFilter.class);
            */
          //  httpSecurity.addFilterAfter(new CsrfTokenLogger(), CsrfFilter.class);

            httpSecurity.httpBasic(c->{
                c.realmName("USER ACCESS");
                c.authenticationEntryPoint(new CustomEntryPoint());
            });

            httpSecurity.authorizeHttpRequests(request->{
                        request.requestMatchers(HttpMethod.GET,"/hello/**").authenticated();
                        request.requestMatchers(HttpMethod.GET, "/*/other").hasAuthority("read");
                        request.requestMatchers("/user/\\d+").authenticated();
                        // request.requestMatchers("/user/{code:^[0-9]*$}").permitAll();
                        request.requestMatchers(HttpMethod.POST, "/user/create").permitAll();
                        request.anyRequest().authenticated();
                    }).formLogin(c->{
                        c.defaultSuccessUrl("/home",true)
                                .successHandler(authSuccessHandler)
                                .failureHandler(authFailureHandler);});

            httpSecurity.logout(logout->{
                logout.logoutUrl("/logout");
                logout.invalidateHttpSession(true);
                logout.clearAuthentication(true);
            });

            httpSecurity.authenticationProvider(authenticationProvider);

            httpSecurity.csrf().disable();

            return httpSecurity.build();
    }

}
