package com.rafaelswr.springsecurityindeep.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryUserDetailsService implements UserDetailsService {

    private List<UserDetails> autorities;

    public InMemoryUserDetailsService(List<UserDetails> autorities) {
        this.autorities = autorities;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        return autorities.stream().filter(user->user.getUsername().equals(username)).findFirst()
                .orElseThrow(()-> new UsernameNotFoundException("USER NOT FOUND"));
    }
}
