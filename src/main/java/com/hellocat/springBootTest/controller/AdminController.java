package com.hellocat.springBootTest.controller;

import com.hellocat.springBootTest.domen.Role;
import com.hellocat.springBootTest.domen.User;
import com.hellocat.springBootTest.repository.RoleRepository;
import com.hellocat.springBootTest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public String adminPageWithAllUsers(Model model) {
        List<User> usersInDB = userRepository.findAll();
        List <Role> roleInDB = roleRepository.findAll();
        model.addAttribute("users", usersInDB)
                .addAttribute("roles", roleInDB);
        return "/thymeleaf/admin/adminPage";
    }

    @GetMapping("/update")
    public String adminUpdatePage(@RequestParam(value = "id") String id) {
        try {
            Long idLong = Long.parseLong(id);
            User user = userRepository.getOne(idLong);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject(user);
            return "thymeleaf/admin/updatePage";
        }catch (NumberFormatException e){
            //
        }
        return "/thymeleaf/admin/adminPage";
    }
}
