package com.rafaelswr.springsecurityindeep.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello(){
        return new ResponseEntity<>("Hello!", HttpStatus.OK);
    }

    @GetMapping("/other")
    public ResponseEntity<String> sayOther(){
        return new ResponseEntity<>("Only if you have READ privileges!", HttpStatus.OK);
    }

    @GetMapping("/public")
    public ResponseEntity<String> publicAccess(){
        return new ResponseEntity<>("You're in public domain!", HttpStatus.OK);
    }


}
