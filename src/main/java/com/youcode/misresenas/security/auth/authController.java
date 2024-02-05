package com.youcode.misresenas.security.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class authController {
    // Login form
    @RequestMapping("/login")
    public String login() {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        return "login";
    }

    // Login form with error
//    @RequestMapping("/login-error")
//    public String loginError(Model model) {
//        model.addAttribute("loginError", true);
//        return "login";
//    }
}