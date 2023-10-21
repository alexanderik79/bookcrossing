package com.example.demo.controllers;

import com.example.demo.services.BookService;
import com.example.demo.services.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class AdminPageController {
    private final BookService bookService;
    private final UserService userService;

    public AdminPageController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }
}
