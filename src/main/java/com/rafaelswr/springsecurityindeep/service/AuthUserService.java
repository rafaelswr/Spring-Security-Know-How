package com.rafaelswr.springsecurityindeep.service;

import com.rafaelswr.springsecurityindeep.model.AuthUser;
import com.rafaelswr.springsecurityindeep.repository.AuthUserRepository;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService {

    public final PasswordEncoder passwordEncoder;
    public final AuthUserRepository authUserRepository;

    @Autowired
    public AuthUserService(AuthUserRepository authUserRepository, PasswordEncoder passwordEncoder) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder ;
    }

    public String createUser(AuthUser authUser) {
        authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));

        AuthUser savedUser = authUserRepository.save(authUser);

        if (savedUser.isEnabled()) {
            return "Added Successfully";
        } else {
            throw new PersistenceException("Failed to save or enable user.");
        }

    }
}