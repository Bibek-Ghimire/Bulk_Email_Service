package com.EmailService.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.EmailService.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showForm() {
        return "register";
    }

    @PostMapping("/save")
    public String saveUser(
            @RequestParam String name,
            @RequestParam String email,
            Model model
    ) {

        userService.saveUser(name, email);

        model.addAttribute("success", "User saved successfully");
        return "register";
    }
}
