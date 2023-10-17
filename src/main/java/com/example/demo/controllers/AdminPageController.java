package com.example.demo.controllers;

import com.example.demo.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPageController {
    private final UserService userService;

    public AdminPageController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/adminpage")
    public String adminPage() {
            return "adminpage";
    }

}
