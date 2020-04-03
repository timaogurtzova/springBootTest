package com.hellocat.springBootTest.controller;

import com.hellocat.springBootTest.domen.Role;
import com.hellocat.springBootTest.domen.User;
import com.hellocat.springBootTest.repository.RoleRepository;
import com.hellocat.springBootTest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public String showAdminPage(Model model) {
        List<User> users = userRepository.findAll();
        List <Role> roles = roleRepository.findAll();
        model.addAttribute("users", users)
                .addAttribute("roles", roles)
                .addAttribute("userNew", new User());
        return "admin/adminPage";
    }

    @GetMapping("/update/{id}")
    public String showUpdatePage(@PathVariable String id, Model model) {
        try {
            Long idLong = Long.parseLong(id);
            User user = userRepository.getOne(idLong);
            model.addAttribute("user", user)
                        .addAttribute("roles", roleRepository.findAll());
            return "admin/updatePage";
        }catch (NumberFormatException e){
            //
        }
        return "redirect:";
    }

    @PostMapping("update/{id}")
    public String updateUser (@PathVariable("id") long id, User user) {
        userRepository.save(user);
        return "redirect:";
    }

    @GetMapping("/delete")
    public String deleteUser (@PathVariable(value = "id") String id) {
        userRepository.deleteById(Long.parseLong(id));
        return "redirect:";
    }

    @PostMapping("/add")
    public String addUser (User user) {
        userRepository.save(user);
        return "redirect:";
    }
}
