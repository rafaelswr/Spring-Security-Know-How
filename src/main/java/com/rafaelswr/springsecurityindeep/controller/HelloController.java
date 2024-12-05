package com.rafaelswr.springsecurityindeep.controller;

import com.rafaelswr.springsecurityindeep.model.AuthUser;
import com.rafaelswr.springsecurityindeep.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutor;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.*;
import java.util.logging.Logger;

@RestController
@RequestMapping("/")
@EnableAsync
public class HelloController {

    private final Logger logger = Logger.getLogger(HelloController.class.getName());

    public final AuthUserService authUserService;

    @Autowired
    public HelloController(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello(Authentication auth){
        return new ResponseEntity<>("Hello " + auth.getName() + "!!", HttpStatus.OK);
    }

    @GetMapping("/other")
    public ResponseEntity<String> sayOther(){
        return new ResponseEntity<>("Only if you have READ privileges!", HttpStatus.OK);
    }

    @GetMapping("/public")
    public String publicAccess() throws ExecutionException, InterruptedException {

        Callable<String> task = ()->{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            return auth.getName();
        };

        ExecutorService e = new DelegatingSecurityContextExecutorService(Executors.newCachedThreadPool());

        try{
            //get result of the async task
            return "Ciao, " + e.submit(task).get() + "!";
        } finally {
            e.shutdown();
        }

    }

    @Async
    @GetMapping("/log")
    public void getUserLog(Authentication auth ) {
        String username = auth.getName();

        logger.info("User: , " + username);
    }

    @PostMapping("/user/create")
    public ResponseEntity<String> createUserForAuth(@RequestBody AuthUser authUser){
        return new ResponseEntity<>(authUserService.createUser(authUser), HttpStatus.CREATED);
    }

}
