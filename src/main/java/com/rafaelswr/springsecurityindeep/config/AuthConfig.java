package com.rafaelswr.springsecurityindeep.config;

import com.rafaelswr.springsecurityindeep.methodAuthorization.DocumentsPermissionEvaluator;
import com.rafaelswr.springsecurityindeep.model.AuthUser;
import com.rafaelswr.springsecurityindeep.model.SimpleUser;
import com.rafaelswr.springsecurityindeep.repository.AuthUserRepository;
import com.rafaelswr.springsecurityindeep.repository.SimpleUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import javax.sql.DataSource;

@Configuration
@EnableMethodSecurity
public class AuthConfig {

    private final DocumentsPermissionEvaluator evaluator;

    @Autowired
    public AuthConfig(DocumentsPermissionEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    @Bean
    protected MethodSecurityExpressionHandler createExpressionHandler(){
        var expressionHandler = new DefaultMethodSecurityExpressionHandler();

        expressionHandler.setPermissionEvaluator(evaluator);
        return expressionHandler;
    }
    @Bean
    public UserDetailsService userDetailsService(){
        var user1 = User.withUsername("natalie")
                .password("1223")
                .roles("admin")
                .build();

        var user2 = User.withUsername("emma")
                .password("1223")
                .roles("manager")
                .build();

        var inMemory = new InMemoryUserDetailsManager();
        inMemory.createUser(user1);
        inMemory.createUser(user2);

        return inMemory;

    }


    /*
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
            //userDetailsService.createUser(newUser);

            return userDetailsManager;
        }
    */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
        //return PasswordEncoderFactories.createDelegatingPasswordEncoder();
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
