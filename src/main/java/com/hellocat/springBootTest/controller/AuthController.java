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
            model.addAttribute("name", user.getName());
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed");
            return "signUp";
        }
        return "user/userpage";
    }

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(name = "error", required = false) Boolean error,
                        Model model) {
        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("error", "true");
        }
        return "signIn";
    }

    @PostMapping ("/login")
    public String userAuthentication(@RequestParam(name = "username") String name,
                                     @RequestParam(name = "userpassword") String password,
                                     Model model) {
       try {
           User user = userService.findUserByName(name);
           if (user.getPassword().equals(password)) {
               model.addAttribute("name", user.getName());
               return "user/userpage";
           }
       }catch (Exception e) {

       }

        return "signIn";
    }


}
