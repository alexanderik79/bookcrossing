package com.example.demo.controllers;

import com.example.demo.entity.Book;
import com.example.demo.entity.User;
import com.example.demo.services.BookService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NewBookController {
    BookService bookService;
    UserService userService;

    @Autowired
    public NewBookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("/newbooks")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "newbooks";
    }
    @PostMapping("/newbooks")
    public String addNewBook(@ModelAttribute Book book) {
        bookService.saveBook(book);
        return "redirect:/dashboard";
    }
}