package com.hellocat.springBootTest.controller;

import com.hellocat.springBootTest.domen.User;
import com.hellocat.springBootTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("userForm", new User());
        return "signUp";
    }

    @PostMapping("/registration")
    public String userRegistration(@ModelAttribute("userForm") User user,
                                   Model model) {
        try {
            userService.saveUser(user);
            model.addAttribute("user", user);
            return "redirect:/user";
        } catch (Exception e) {

        }
        model.addAttribute("error", "Registration failed");
        return "signUp";
    }

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(name = "error", required = false) Boolean error,
                               Model model) {
        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("error", "invalid username or password");
        }
        return "signIn";
    }

}
