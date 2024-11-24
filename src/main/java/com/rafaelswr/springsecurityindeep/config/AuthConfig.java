package com.rafaelswr.springsecurityindeep.config;

import com.rafaelswr.springsecurityindeep.model.AuthUser;
import com.rafaelswr.springsecurityindeep.model.SimpleUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class AuthConfig {

    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails user = User.withUsername("rafael")
                .password(passwordEncoder().encode("1223"))
                .roles("user").build();

        UserDetails newUser = new AuthUser(
                new SimpleUser("Rafa","Borges"), "rafaelswr", passwordEncoder().encode("1223"), "read");

        List<UserDetails> users = List.of(user, newUser);
        //userDetailsService.createUser(user);
        //userDetailsService.createUser(newUser);

        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
