package com.github.daedalus.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class PageController {
    @GetMapping("/")
    public String root(){
        return "redirect:/index";
    }

    @GetMapping("/index")
    public ModelAndView index(){
        ModelAndView modelIndex = new ModelAndView("index");
        return modelIndex;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "logout";
    }

    @GetMapping("/create_account")
    public String createAccount(){
        return "create_account";
    }
}
