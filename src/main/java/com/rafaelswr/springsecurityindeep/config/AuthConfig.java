package com.rafaelswr.springsecurityindeep.config;

import com.rafaelswr.springsecurityindeep.model.AuthUser;
import com.rafaelswr.springsecurityindeep.model.SimpleUser;
import com.rafaelswr.springsecurityindeep.repository.AuthUserRepository;
import com.rafaelswr.springsecurityindeep.repository.SimpleUserRepository;
import org.aspectj.weaver.ast.HasAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;
import java.security.SecureRandom;
import java.util.*;

@Configuration
public class AuthConfig {

    private final AuthUserRepository authUserRepository;
    private final SimpleUserRepository simpleUserRepository;
    @Autowired
    public AuthConfig(AuthUserRepository authUserRepository, SimpleUserRepository simpleUserRepository) {
        this.authUserRepository = authUserRepository;
        this.simpleUserRepository = simpleUserRepository;
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);

        userDetailsManager.setUsersByUsernameQuery("SELECT username, password, enabled FROM auth_user WHERE username = ?");

        userDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT username, authority FROM auth_user WHERE username = ?");

       // SimpleUser simpleUser = new SimpleUser("Rafa","Borges");
       // AuthUser authUser = new AuthUser( simpleUser , "rafaelswr", passwordEncoder().encode("1223"), "read");
        // List<UserDetails> users = List.of(authUser);
       /* UserDetails user = User.withUsername("rafael")
                .password(passwordEncoder().encode("1223"))
                .roles("user").build();

        UserDetails newUser = new AuthUser(
                new SimpleUser("Rafa","Borges"), "rafaelswr", passwordEncoder().encode("1223"), "read");

        List<UserDetails> users = List.of(user, newUser);
        //userDetailsService.createUser(user);
        //userDetailsService.createUser(newUser);*/

        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        /*
        SecureRandom s = new SecureRandom();
        HashMap<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder(4, s));
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        return new DelegatingPasswordEncoder("bcrypt", map);

        */

        //SecureRandom s = new SecureRandom();
        //return new BCryptPasswordEncoder(10, s);
        //return new SCryptPasswordEncoder(16384, 8, 1, 32, 64);
        //return new SHA512PasswordEncoder();
        //return new NoOpPasswordEncoder.getInstance();


    }


}
