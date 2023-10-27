package com.example.demo.controllers;

import com.example.demo.entity.User;
import com.example.demo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    private final UserService userService;
    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }


    @PostMapping("/register")
    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (userService.usernameExists(user.getUsername())) {
            bindingResult.rejectValue("username", "error.user", "This username already exists");
            return "register";
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.processRegistration(user);
        return "redirect:/login";
    }

}
