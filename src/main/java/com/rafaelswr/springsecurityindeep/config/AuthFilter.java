package com.rafaelswr.springsecurityindeep.config;

import com.rafaelswr.springsecurityindeep.filters.AuthenticationLoggingFilter;
import com.rafaelswr.springsecurityindeep.filters.RequestValidationFilter;
import com.rafaelswr.springsecurityindeep.filters.StaticKeyAuthenticationFilter;
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

            // (Demo) Filter that validates if the request contains a header called 'Request-ID' (example),
            // and another one after the BasicAuthenticationFilter to log the value of the 'Request-ID' header.
           /* httpSecurity.addFilterBefore(new RequestValidationFilter(), BasicAuthenticationFilter.class)
                    .addFilterAt(new StaticKeyAuthenticationFilter(), BasicAuthenticationFilter.class).addFilterAfter(new AuthenticationLoggingFilter(), BasicAuthenticationFilter.class);
*/


            httpSecurity.httpBasic(Customizer.withDefaults());

            httpSecurity.authorizeHttpRequests(request->{
                        request.requestMatchers("/hello").authenticated();
                        request.requestMatchers("/other").hasRole("read");
                        request.requestMatchers("/salir").authenticated();
                        request.requestMatchers("/user/create").permitAll();
                        request.anyRequest().permitAll();

                    }).formLogin(Customizer.withDefaults());
            httpSecurity.logout(logout->{
                logout.logoutUrl("/custom-logout");
                logout.invalidateHttpSession(true);
                logout.clearAuthentication(true);
            });

            httpSecurity.authenticationProvider(authenticationProvider);

            httpSecurity.csrf().disable();

            return httpSecurity.build();
    }

}
