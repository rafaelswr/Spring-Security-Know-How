package com.rafaelswr.springsecurityindeep.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class MvcController {

    private Logger logger =
            Logger.getLogger(MvcController.class.getName());

    @GetMapping("/home")
    public String home(){
        return "home.html";
    }

    @GetMapping("/error")
    public String error(){
        return "error.html";
    }

    @GetMapping("/main")
    public String main(){
        return "main.html";
    }


    @PostMapping("/product/add")
    public String add(@RequestParam String name) {
        logger.info("Adding product " + name);
        return "main.html";
    }

}
