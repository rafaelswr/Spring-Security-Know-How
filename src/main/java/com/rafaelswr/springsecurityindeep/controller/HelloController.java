package com.rafaelswr.springsecurityindeep.controller;

import com.rafaelswr.springsecurityindeep.model.AuthUser;
import com.rafaelswr.springsecurityindeep.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class HelloController {

    public final AuthUserService authUserService;

    @Autowired
    public HelloController(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello(){
        return new ResponseEntity<>("Hello!", HttpStatus.OK);
    }

    @GetMapping("/other")
    public ResponseEntity<String> sayOther(){
        return new ResponseEntity<>("Only if you have READ privileges!", HttpStatus.OK);
    }

    @GetMapping("/public")
    public ResponseEntity<String> publicAccess(Authentication auth){

       // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<>(auth.getName()+ ", you're in public domain!", HttpStatus.OK);
    }

    @PostMapping("/user/create")
    public ResponseEntity<String> createUserForAuth(@RequestBody AuthUser authUser){
        return new ResponseEntity<>(authUserService.createUser(authUser), HttpStatus.CREATED);
    }


}
