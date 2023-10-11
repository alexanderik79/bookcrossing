package com.example.demo.controllers;

import com.example.demo.entity.Book;
import com.example.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class MyBooksController {
    private final BookService bookService;
    @Autowired
    public MyBooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/my-books")
    public String myBooks(@RequestParam("user_id") Long userId, Model model) {
        List<Book> listOfBooks = bookService.findByUserId(userId);
        model.addAttribute("books", listOfBooks);
        return "my-books";
    }


}
