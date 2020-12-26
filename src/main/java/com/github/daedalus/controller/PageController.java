package com.github.daedalus.controller;

import com.github.daedalus.database.UsersRepository;
import com.github.daedalus.service.LoginSessionService;
import liquibase.pro.packaged.U;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
@Slf4j
@RequiredArgsConstructor
public class PageController {
    private final LoginSessionService loginSessionService;
    private final UsersRepository usersRepository;

    @GetMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView modelIndex = new ModelAndView("index");
        return modelIndex;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(@CookieValue(name = "session-id", required = false) UUID sessionId, HttpServletResponse response) {
        loginSessionService.deleteSession(sessionId);

        Cookie cookie = new Cookie("session-id", "");
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(-1);

        response.addCookie(cookie);
        return "redirect:/index";
    }

    @GetMapping("/create_account")
    public String createAccount() {
        return "create_account";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
