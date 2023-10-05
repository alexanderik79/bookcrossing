package com.example.demo.controllers;

import com.example.demo.entity.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DashboardController {

    private final UserService userService;

    @Autowired
    public DashboardController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/dashboard")
//    public String dashboardPage(Model model) {
//        User user = userService.getUserByLogin("alex");
//        if (user != null) {
//            model.addAttribute("user", user);
//            return "dashboard";
//        } else {
//            // Обработка случая, когда пользователь не найден
//            return "redirect:/error";
//        }
//    }

    @GetMapping("/dashboard")
    public String dashboardPage(Model model, @RequestParam(name = "login") String login) {
        User user = userService.getUserByLogin(login);
        if (user != null) {
            model.addAttribute("user", user);
            return "dashboard";
        } else {
            // Обработка случая, когда пользователь не найден
            return "redirect:/error";
        }
    }

}