package com.technowebtp.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomepageController {

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @RequestMapping("/hello")
    public String showhello(){
        return "hello";
    }
}
