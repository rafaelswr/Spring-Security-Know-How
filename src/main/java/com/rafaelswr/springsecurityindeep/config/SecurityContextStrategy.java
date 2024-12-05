package com.rafaelswr.springsecurityindeep.config;

import org.hibernate.sql.results.graph.Initializer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Configuration
public class SecurityContextStrategy {

    //Set SecurityContext strategy from MODE_THREADLOCAL (default) to MODE_INHERITABLETHREADLOCAL
    @Bean
    public InitializingBean initializingBean(){
        return (()-> SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_THREADLOCAL));
    }

    /*
        //Set SecurityContext strategy from MODE_THREADLOCAL (default) to MODE_GLOBAL
        @Bean
        public InitializingBean initializingBean(){
            return (()-> SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_GLOBAL));
        }
    */
}
