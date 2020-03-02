package com.example.basicsecurity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    //4.02 REDIRECT TO CUSTOM "LOGIN.HTML" PAGE
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    //4.03 USING ROLES FOR PAGE PERMISSIONS
    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

    @RequestMapping("/secure")
    public String secure(){
        return "secure";
    }
}
