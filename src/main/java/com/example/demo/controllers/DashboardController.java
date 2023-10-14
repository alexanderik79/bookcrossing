package com.example.demo.controllers;

import com.example.demo.entity.Book;
import com.example.demo.entity.User;
import com.example.demo.services.BookService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DashboardController {

    private final UserService userService;
    private final BookService bookService;

    @Autowired
    public DashboardController(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

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