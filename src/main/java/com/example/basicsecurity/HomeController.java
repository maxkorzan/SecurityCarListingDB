package com.example.basicsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class HomeController {

    //4.06 - USER REGISTRATION
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){
        model.addAttribute("user", user);
        if (result.hasErrors()){
            return "registration";
        } else {
            userService.saveUser(user);
            model.addAttribute("message", "User Account Created");
        }
        return "index";
    }

    //////////////////////////////////////////////////////

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

    //////////////////////////////////////////////////////

    //4.05 PERSISTING CURRENT USER INFO
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/secure")
//    public String secure(){
//        return "secure";
//    }
    public String secure(Principal principal, Model model){
        String username = principal.getName();
        model.addAttribute("user", userRepository.findByUsername(username));
        return "secure";
    }


}
